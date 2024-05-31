package com.example.enumeration;

import lombok.Getter;

@Getter
public enum OtherBaseEnum {

    SUBMIT_GET("GET","get提交"),
    SUBMIT_POST("POST","post提交"),

    ALIBABA_CLOUD_REAL_NAME_SUCCESS_CODE("0000", "阿里云个人实名认证返回状态码-> 成功"),
    ALIBABA_CLOUD_REAL_NAME_AUTHENTICATION_URL("https://idenauthen.market.alicloudapi.com/idenAuthentication", "阿里云实名认证url"),

    ALIBABA_CLOUD_COMPANY_CERTIFICATION_HOST("https://smcomp4.market.alicloudapi.com","企业认证网址"),
    ALIBABA_CLOUD_COMPANY_CERTIFICATION_PATH("/companyfour/check","企业认证路径"),

    ALIBABA_CLOUD_SEARCH_COMPANY_INFO_SUCCESS_CODE("200", "阿里云查询企业信息返回状态码-> 成功"),
    ALIBABA_CLOUD_SEARCH_COMPANY_INFO_HOST("https://idcardside.market.alicloudapi.com","查询企业信息的网址"),
    ALIBABA_CLOUD_SEARCH_COMPANY_INFO_PATH("/business_license/ocr","查询企业信息的路径"),

    ALIBABA_CLOUD_SEND_MESSAGE_SUCCESS_CODE("0", "阿里云发送短信成功码"),
    ALIBABA_CLOUD_SEND_MESSAGE_PREFIX("e68dbd9affc54720ba520f6185973119", "阿里云发送短信签名"),
    ALIBABA_CLOUD_SEND_MESSAGE_TEMPLATE("02551a4313154fe4805794ca069d70bf", "阿里云发送短信模板"),
    ALIBABA_CLOUD_SEND_MESSAGE_HOST("https://gyytz.market.alicloudapi.com","阿里云发送短信网址"),
    ALIBABA_CLOUD_SEND_MESSAGE_PATH("/sms/smsSend","阿里云发送短信路径   "),

    ALIBABA_CLOUD_APP_CODE("1b1f7a0d763b43fe84164b47b2d0fa42","阿里云appCode"),
    ALIBABA_CLOUD_CONTENT_TYPE("application/x-www-form-urlencoded; charset=UTF-8","阿里云提交格式");

    OtherBaseEnum(String data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    //    第三方
    private final String data;
    private final String msg;

}
