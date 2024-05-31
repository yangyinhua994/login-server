import atexit
import shutil
import threading

import git
import psutil
from flask import Flask, request, jsonify
import subprocess
import os
import json

app = Flask(__name__)
pids = []
IS_CLONE_CODE = False


def stop_current_process():
    pid = get_pid_by_port(APP_RUN_PORT)
    if pid:
        stop_run_by_pid(pid)
    for pid in pids:
        stop_run_by_pid(pid)
        pids.remove(pid)


def stop_run_by_pid(pid):
    try:
        p = psutil.Process(pid)
        if p.is_running():
            p.terminate()
            p.wait()
            print(f"已终止进程，PID: {pid}")
    except psutil.NoSuchProcess:
        print(f"进程 PID: {pid} 不存在")


def get_pid_by_port(port):
    port = int(port)
    connections = psutil.net_connections()
    for conn in connections:
        if conn.laddr.port == port:
            return conn.pid


def run_commands(commands):
    global pids
    stop_current_process()
    for command in commands:
        process = subprocess.Popen(command, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
        pid = process.pid
        print(f"运行命令 {' '.join(command)}，PID: {pid}")

        def print_output(proc):
            for line in iter(proc.stdout.readline, ''):
                print(line.strip())

        output_thread = threading.Thread(target=print_output, args=(process,))
        output_thread.start()
        if "java" in command:
            process.wait()
            output_thread.join()
        pids.append(pid)
    print("所有命令运行完成")


@app.route('/webhook', methods=['POST'])
def webhook():
    print("接收到git更新代码请求")
    data = request.json
    if data and 'ref' in data and data['ref'] == f'refs/heads/{BRANCH_NAME}':
        update_repo()
        return jsonify({"status": "success"}), 200
    return jsonify({"status": "ignored"}), 200


def update_repo():
    try:
        os.chdir(REPO_PATH)
        run_commands()
        print("Repository updated successfully.")
    except subprocess.CalledProcessError as e:
        print(f"Error updating repository: {e}")


def on_exit():
    stop_current_process()
    print('程序结束时已调用 stop_current_process')


def remove_dir(dir_path):
    shutil.rmtree(dir_path)


if __name__ == '__main__':
    if not os.path.exists("config_network.json"):
        os.chdir("../..")
    try:
        with open("config_network.json", "r") as f:
            config_json = json.load(f)
            REPO_PATH = str(config_json["REPO_PATH"])
            GIT_URL = str(config_json["GIT_URL"])
            IP = str(config_json["IP"])
            BRANCH_NAME = str(config_json["BRANCH_NAME"])
            APP_RUN_PORT = int(config_json["APP_RUN_PORT"])
            RUN_APP_COMMANDS = list(config_json["RUN_APP_COMMANDS"])
            REMOTE_NAME = "origin"
    except Exception as e:
        print(f"解析config_network.json出错，请检查config_network.json是否存在或格式是否有误: {e}")
    REPO_NAME = GIT_URL.split("/")[-1].replace(".git", "")
    REPO_PATH = os.path.join(REPO_PATH, REPO_NAME)
    if IS_CLONE_CODE:
        if os.path.exists(REPO_PATH):
            remove_dir(REPO_PATH)
        try:
            os.makedirs(REPO_PATH)
            os.chdir(REPO_PATH)
            print("正在拉取代码...")
            git.Repo.clone_from(GIT_URL, REPO_PATH)
        except Exception as e:
            print(f'克隆代码仓库时出错: {e}')
            remove_dir(REPO_PATH)
            exit(1)
    repo = git.Repo(REPO_PATH)
    try:
        repo.git.pull(REMOTE_NAME, BRANCH_NAME)
        stop_current_process()
        run_commands()
    except Exception as e:
        print(f'初次启动时拉取代码出错: {e}')

    atexit.register(on_exit)

    app.run(host='0.0.0.0', port=APP_RUN_PORT)
