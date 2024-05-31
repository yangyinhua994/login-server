package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {

    /**
     * 用户ID
     */
    @TableId("id")
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
     * 密码
     */
    private String password;

    /**
     * 身份证号码
     */
    private String idCard;

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
     * 状态[1:删除,2:启用,3:禁用]
     */
    private int status;

    /**
     * 是否设置过密码(1:是， 2：不是)
     */
    private int isSetPassword;

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

    public User() {
    }
}