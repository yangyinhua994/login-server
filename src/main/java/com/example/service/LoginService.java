package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.User;
import com.example.respone.Result;
import com.example.vo.UserVo;

public interface LoginService extends IService<User> {
    Result<UserVo> loginSmsCode(String phoneNumber, String smsCode);

    Result<UserVo> loginSmsCode(String phoneNumber, String smsCode, String userName, String password);

    Result<UserVo> loginPassword(String phoneNumber, String password);

    Result<UserVo> getSmsCode(String phoneNumber);

    Result<UserVo> refresh(String refreshJwt);

}
