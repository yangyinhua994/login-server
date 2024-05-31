package com.example.vo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;

@Data
public class UserVo {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 邮箱
     */
    private String userEmail;

    /**
     * 用户类型（0为未知用户，1为游客用户，2为会员用户，3为企业用户）
     */
    private int userType;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    /**
     * 是否实名(1:是， 2：不是)
     */
    private int isSetRealName;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 省份
     */
    private String province;

    /**
     * 市
     */
    private String county;

    /**
     * 城市（区、县）
     */
    private String city;

    /**
     * 性别（M:男 F：女）
     */
    private String sex;

    /**
     * 年龄
     */
    private int age;

    /**
     * 刷新token
     */
    private String refreshJwt;

    /**
     * token
     */
    private String token;

    /**
     * 短信验证码
     */
    private String smsCode;

    public UserVo() {
    }
}