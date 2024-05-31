package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("user_role")
public class UserRole {

    /**
     * id
     */
    @TableId("id")
    private Long id;

    /**
     * 关联用户表id
     */
    private Long userId;

    /**
     * 关联公司表id
     */
    private Long companyId;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色类型[0：未知，1：个人身份，2企业身份]
     */
    private int roleType;

    /**
     * 是否正在使用[1：未使用，2：正在使用]
     */
    private int isUsed;

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

    public UserRole() {
    }
}