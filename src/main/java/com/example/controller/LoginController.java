package com.example.controller;

import com.example.msg.MsgEnum;
import com.example.respone.Result;
import com.example.service.LoginService;
import com.example.utils.CheckUtil;
import com.example.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = "登录管理接口")
@RestController
@RequestMapping("/api")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @ApiOperation("获取验证码")
    @GetMapping("/getSmsCode")
    private Result<UserVo> getSmsCode(String phoneNumber) {
        CheckUtil.checkEmpty(phoneNumber, "phoneNumber");
        return loginService.getSmsCode(phoneNumber);

    }

    @ApiOperation("验证码登录")
    @PostMapping("/loginSmsCode")
    public Result<UserVo> loginSmsCode(String phoneNumber, String smsCode, @RequestParam(required = false) String userName, @RequestParam(required = false) String password) {
        CheckUtil.checkEmptyAll("phoneNumber or smsCode", phoneNumber, smsCode);
        if (!CheckUtil.isPhoneNumber(phoneNumber)) {
            return Result.fail(MsgEnum.PHONE_NUMBER_ERROR);
        }
        if (CheckUtil.isEmpty(userName) || CheckUtil.isEmpty(password)){
            return loginService.loginSmsCode(phoneNumber, smsCode);
        }else {
            return loginService.loginSmsCode(phoneNumber, smsCode, userName, password);
        }

    }

    @ApiOperation("电话号码密码登录")
    @PostMapping("/loginPassword")
    public Result<UserVo> loginPassword(String phoneNumber, String password) {
        CheckUtil.checkEmptyAll("phoneNumber or password", phoneNumber, password);
        if (CheckUtil.isPhoneNumber(phoneNumber)) {
            return loginService.loginPassword(phoneNumber, password);
        } else {
            return Result.fail(MsgEnum.PHONE_NUMBER_ERROR);
        }

    }

    @ApiOperation("通过refresh刷新token")
    @PostMapping("/refresh")
    public Result<UserVo> refresh(String refreshJwt) {
        CheckUtil.checkEmpty(refreshJwt, "refreshJwt");
        return loginService.refresh(refreshJwt);

    }

    @GetMapping("/test2")
    public String test(){
        return "test";
    }

}













