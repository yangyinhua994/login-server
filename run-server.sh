#!/bin/bash

docker_name=login-server
port=7070

user=root
server_addr=192.168.0.232
jar_save_path=/home/kantu3d/docker/images/java_image/${docker_name}
ssh_url=$user@$server_addr
scp_url=$user@$server_addr:$jar_save_path

mvn clean install
mvn package
ssh $ssh_url "mkdir -p ${jar_save_path} && exit"
scp -r Dockerfile target/*.jar $scp_url

echo "docker_name = ${docker_name}"
ssh $ssh_url "
    if docker ps -a --format '{{.Names}}' | grep -q '${docker_name}$'; then
        echo '停止并删除容器 ${docker_name}'
        docker stop ${docker_name} && docker rm ${docker_name}
        echo '删除关联的镜像 ${docker_name}'
        docker rmi ${docker_name}
    else
        echo '${docker_name}容器未在运行'
    fi

    command -v lsof >/dev/null 2>&1
    if [ $? -ne 0 ]; then
       echo 'lsof 未安装'
       sudo apt-get install lsof
    fi
        port_pid=\$(lsof -t -i:${port})
        if [ -n \"\$port_pid\" ]; then
          echo '端口${port}已被占用，正在结束占用该端口的程序...'
          # 在这里添加结束占用端口的程序的操作
          kill -9 \${port_pid}
          echo '占用端口的程序已结束'
        else
          echo '端口${port}未被占用'
        fi

    docker build -t ${docker_name} ${jar_save_path} && nohup docker run -d -p ${port}:${port} --name ${docker_name} ${docker_name} && disown
"
echo ${docker_name}启动成功
exit 0