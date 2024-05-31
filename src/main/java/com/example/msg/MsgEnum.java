package com.example.msg;

import lombok.Getter;

/*
 *返回提示消息
 **/
@Getter
public enum MsgEnum {

    DATA_NOT_EXIST_OR_DISABLED(500, "数据不存在或被禁用"),
    DATA_DOES_NOT_EXIST_OR_THERE_ARE_MULTIPLE_ENTRIES(500, "数据不存在或存在多条"),

    COMPANY_ID_ERROR(500, "公司id有误," + DATA_NOT_EXIST_OR_DISABLED),
    COMPANY_ROLE_MULTIPLE_ENTRIES_EXIST(500, "用户存在多条该公司的数据"),

    REFRESH_JWT_EXPIRE(500, "登录已过期，请重新登录"),

    LOGIN_SERVICE_NOT_RUNNING(500, "调用登陆服务失败，登陆服务未运行"),
    LOGIN_SERVICE_RESPONSE_NULL(500, "登陆服务响应数据为空"),

    MESSAGE_PROCESSED(500, "消息已处理"),
    MESSAGE_NOT_SEND_ROLE_ID(500, "该消息没有发送人id，数据有误"),
    MESSAGE_NOT_RECEIVE_ROLE_ID(500, "该消息没有接收人id，数据有误"),
    MESSAGE_APPLY_WAS_SENT(500, "已发送过申请，请等待公司管理者处理"),
    MESSAGE_WAITING_FOR_USER_CONSENT(500, "已发送过申请，请等待用户同意"),
    MESSAGE_PROCESSING_STATUS_IS_EMPTY(500, "请选择通过或拒绝"),
    MESSAGE_PROCESSING_EXCEPTION(500, "消息处理异常"),
    MESSAGE_RECEIVE_ROLE_ID_PHONE_NUMBER(500, "消息的receive_role_id和receive_phone_number都为空，数据有误"),

    CERTIFICATE_HAS_EXPIRED(500, "软件使用期限已到，请联系工作人员申请新的证书文件"),
    CERTIFICATE_FILE_DOES_NOT_EXIST(500, "证书文件不存在，请上传"),
    CERTIFICATE_FILE_NOT_VALID(500, "证书文件不是有效的或已过期"),

    REPEAT_OF_USER_NAME_ERROR(500, "未知错误"),

    ID_OR_PASSWORD_NULL_ERROR(500, "id或密码为空"),
    ID_NULL_ERROR(500, "id为空"),
    ID_ERROR(500, "id异常，没有该id的数据"),

    SMS_CODE_ERROR(500, "验证码错误或已失效"),

    ROLES_ID_ERROR(500, "角色id有误," + DATA_NOT_EXIST_OR_DISABLED),
    ROLES_SWITCHING_EXCEPTION(500, "切换用户角色失败"),
    ROLES_NOT_EXIST(500, "该角色不存在"),
    ROLES_PHONE_NUMBER_NULL(500, "切换到个人用户角色失败,电话号码为空"),
    ROLES_IN_COMPANY(500, "该角色已加入公司"),
    ROLES_APPLICATION_REJECTED(500, "已拒绝该申请"),
    ROLES_PERSONAL_IDENTITY_INFORMATION_EXCEPTION(500, "用户角色信息有误，不存在个人身份角色信息或存在多条"),

    USER_IN_COMPANY(500, "用户已在该公司"),
    USER_NOT_REGISTERED_ERROR(500, "用户未注册"),
    USER_DOES_NOT_EXIST(500, "用户不存在"),
    USER_OR_PASSWORD_ERROR(500, "用户名或密码错误"),
    USER_PASSWORD_HAS_BEEN_SET(500, "已设置过密码"),
    USER_ORIGINAL_PASSWORD_ERROR(500, "原始密码输入错误"),


    PHONE_NUMBER_ERROR(500, "手机号码错误"),
    PHONE_NUMBER_OR_PASSWORD_ERROR(500, "用户名或密码错误"),

    THIRD_PARTY_RESPONSE_ERROR(500, "第三方响应异常"),

    FILE_PROCESSING_EXCEPTION(500, "文件处理异常"),
    FILE_NAME_EMPTY(500, "文件名称为空"),
    FILE_CREATION_EXCEPTION(500, "创建文件异常"),
    FILE_COPY_EXCEPTION(500, "复制文件异常"),
    FILE_DELETE_EXCEPTION(500, "删除文件异常"),
    FILE_NOT_JSON(500, "文件不是json格式"),
    FILE_DATA_STREAM_EXCEPTION(500, "获取文件异常"),
    FILE_PREVIEW_ERROR(111, "文件预览异常"),
    FILE_NOT_FOUND(111, "文件未找到或过期"),
    FILE_NOT_EXIST(500, "文件不存在"),
    FILE_CONTENT_ERROR(500, "文件内容有误，请检查文件是否为有效文件或文件后缀名是否正确"),
    FILE_SUFFIX_IS_NULL(500, "文件后后缀未找到"),
    FILE_FORMAT_NOT_SUPPORTED(500, "文件格式不支持"),
    FILE_IS_NULL(500, "文件为空"),
    FILE_INSUFFICIENT_VIEWING_PERMISSIONS(500, "你无权查看该文件"),
    FILE_PARSING_EXCEPTIONS(500, "文件解析异常"),

    DANGEROUS_ACCESS_EXCEPTION(500, "危险访问"),

    ALIBABA_CLOUD_RESPONSE_NULL_EXCEPTION(500, "阿里云响应异常，响应数据为空"),

    UNKNOWN_ERROR(500, "服务器正忙，请稍候重试..."),
    UNKNOWN_MASSAGE_HANDLER_STATUS_ERROR(500, "未知的消息处理状态");

    private final int code;

    private final String msg;

    MsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
