package com.example.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDto {

    @ApiModelProperty(value = "用户ID", example = "0")
    private Long id;

    @ApiModelProperty(value = "电话号码")
    private String phoneNumber;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "身份证号码")
    private String idCard;

    @ApiModelProperty(value = "邮箱")
    private String userEmail;

    @ApiModelProperty(value = "用户类型（0为未知用户，1为游客用户，2为会员用户，3为企业用户）", example = "0")
    private int userType;

    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    @ApiModelProperty(value = "更新时间")
    private Timestamp updateTime;

    @ApiModelProperty(value = "状态[1:删除,2:启用,3:禁用]", example = "2")
    private int status;

    @ApiModelProperty(value = "是否设置过密码(1:是， 2：不是)", example = "2")
    private int isSetPassword;

    @ApiModelProperty(value = "是否实名(1:是， 2：不是)", example = "2")
    private int isSetRealName;

    @ApiModelProperty(value = "生日")
    private String birthday;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "市")
    private String county;

    @ApiModelProperty(value = "城市（区、县）")
    private String city;

    @ApiModelProperty(value = "性别（M:男 F：女）")
    private String sex;

    @ApiModelProperty(value = "年龄", example = "0")
    private int age;

    @ApiModelProperty(value = "当前页码", example = "1")
    private int pageNum;

    @ApiModelProperty(value = "每页记录数", example = "10")
    private int pageSize;

    @ApiModelProperty(value = "刷新token")
    private String refreshJwt;

    @ApiModelProperty(value = "新密码")
    private String newPassword;

    @ApiModelProperty(value = "短信验证码")
    private String smsCode;

    public UserDto() {
    }
}