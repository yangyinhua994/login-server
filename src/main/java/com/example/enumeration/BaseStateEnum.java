package com.example.enumeration;

import lombok.Getter;

@Getter
public enum BaseStateEnum {
    DELETE_UNKNOWN_STATE(0, "未知状态"),
    DELETE_STATE_DELETED(1, "已删除"),
    DELETE_STATE_ENABLE(2, "启用"),
    DELETE_STATE_DISABLE(3, "禁用"),

    COMPANY_ROLE_STATE_DISABLE(4, "已离职或踢出"),

    USER_ALREADY_REAL_NAME(1, "用户已实名"),
    USER_NOT_REAL_NAME(2, "用户未实名"),
    USER_ALREADY_SET_PASSWORD(1, "用户已设置密码"),
    USER_NOT_SET_PASSWORD(2, "用户未设置密码"),
    USER_UNKNOWN_USER_TYPE(0,"未知用户"),
    USER_VISITOR_USER_TYPE(1,"游客用户"),
    USER_MEMBER_USER_TYPE(2 ,"会员用户"),

    USER_ROLE_UNKNOWN_ROLE_TYPE(0,"未知身份"),
    USER_ROLE_PERSON_ROLE_TYPE(1,"个人身份"),
    USER_ROLE_COMPANY_ROLE_TYPE(2,"企业身份"),
    USER_ROLE_STATE_USED(1,"用户角色正在使用状态"),

    USER_ROLE_STATE_NOT_USED(2,"用户角色未使用状态"),

//  消息阅读状态
    MESSAGE_STATE_UNKNOWN(0,"未知"),
    MESSAGE_STATE_READ(1,"已读"),
    MESSAGE_STATE_UNREAD(2,"未读"),
    MESSAGE_HANDLE_STATUS_UNTREATED(0,"未处理"),
    MESSAGE_HANDLE_STATUS_ACCEPT(1,"同意"),
    MESSAGE_HANDLE_STATUS_REFUSE(2,"拒绝"),
    MESSAGE_TYPE_UNKNOWN(0,"未知消息的类型"),
    MESSAGE_TYPE_ORDINARY(1,"普通消息"),
    MESSAGE_TYPE_SYSTEM(2,"系统消息"),
    MESSAGE_TYPE_ENTERPRISE_INVITATION(3,"企业邀请"),
    MESSAGE_TYPE_ENTERPRISE_JOINING(4,"申请加入企业"),

    FILE_IS_ACCESSIBLE(1,"可随时访问"),
    FILE_IS_NOT_ACCESSIBLE(1,"不可随时访问"),

    UPDATE_SUCCESS(1,"修改成功"),
    INSERT_SUCCESS(1,"新增成功");


    BaseStateEnum(int state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    private final int state;

    private final String msg;

    private static final String UNKNOWN_STATE = "未知状态";

}
