package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.User;
import com.example.enumeration.BaseStateEnum;
import com.example.mapper.UserMapper;
import com.example.msg.MsgEnum;
import com.example.respone.Result;
import com.example.service.LoginService;
import com.example.utils.*;
import com.example.vo.UserVo;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl extends ServiceImpl<UserMapper, User> implements LoginService {

    private final UserMapper userMapper;


    private final JwtTokenUtil jwtTokenUtil;

    public LoginServiceImpl(UserMapper userMapper, JwtTokenUtil jwtTokenUtil) {
        this.userMapper = userMapper;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public Result<UserVo> loginSmsCode(String phoneNumber, String smsCode) {
        String loginSmsCode = Redis.getLoginSmsCode(phoneNumber);
        if (!smsCode.equals(loginSmsCode)) {
            return Result.fail(MsgEnum.SMS_CODE_ERROR.getMsg());
        }
        Redis.deleteLoginSmsCode(phoneNumber);
        User user = getUserForPhoneNumber(phoneNumber);
        return Result.success(buildTokens(user));
    }

    @Override
    public Result<UserVo> loginSmsCode(String phoneNumber, String smsCode, String userName, String password) {
        String loginSmsCode = Redis.getLoginSmsCode(phoneNumber);
        if (!smsCode.equals(loginSmsCode)) {
            return Result.fail(MsgEnum.SMS_CODE_ERROR.getMsg());
        }
        Redis.deleteLoginSmsCode(phoneNumber);
        User user = getUserForPhoneNumber(phoneNumber);
        return Result.success(buildTokens(user));
    }


    @Override
    public Result<UserVo> loginPassword(String phoneNumber, String password) {
        password = PasswordUtil.encryption(password);
        User user = getUserForPhoneNumber(phoneNumber);
        if (user == null) {
            return Result.fail(MsgEnum.USER_NOT_REGISTERED_ERROR);
        }
        if (!user.getPassword().equals(password)) {
            return Result.fail(MsgEnum.PHONE_NUMBER_OR_PASSWORD_ERROR);
        } else {
            return Result.success(buildTokens(user));
        }

    }

    private UserVo buildTokens(User user) {
        if (user == null){
            return null;
        }
        UserVo userVo = EntityConversionUtil.entityToVo(user, UserVo.class);
        String accessToken = jwtTokenUtil.generateAccessToken(user.getId(), user.getPhoneNumber());
        String refreshToken = jwtTokenUtil.generateRefreshToken(user.getId(), user.getPhoneNumber());
        userVo.setToken("Bearer " + accessToken);
        userVo.setRefreshJwt(refreshToken);
        return userVo;
    }

    @Override
    public Result<UserVo> getSmsCode(String phoneNumber) {

        String smsCode = SmsCodeUtil.generateSmsCode();
        UserVo userVo = new UserVo();
        userVo.setPhoneNumber(phoneNumber);
        userVo.setSmsCode(smsCode);
        Redis.setLoginSmsCode(phoneNumber, smsCode);
        return Result.success(userVo);

//        String smsCode = SmsCodeUtil.generateSmsCode();
//        int minute = 5;
//        Redis.setLoginSmsCode(phoneNumber, smsCode);
//        Map<String, String> sendMessageHeader = new HashMap<>();
//        sendMessageHeader.put("Authorization", "APPCODE " + OtherBaseEnum.ALIBABA_CLOUD_APP_CODE.getData());
//        Map<String, String> sendMessageQuery = new HashMap<>();
//        sendMessageQuery.put("mobile",phoneNumber);
//        sendMessageQuery.put("param", "**code**:"+smsCode+",**minute**:"+minute);
//        sendMessageQuery.put("smsSignId",OtherBaseEnum.ALIBABA_CLOUD_SEND_MESSAGE_PREFIX.getData());
//        sendMessageQuery.put("templateId",OtherBaseEnum.ALIBABA_CLOUD_SEND_MESSAGE_TEMPLATE.getData());
//        Map<String, String> sendMessageBody = new HashMap<>();
//
//        try {
//            HttpResponse response = HttpUtils.doPost(OtherBaseEnum.ALIBABA_CLOUD_SEND_MESSAGE_HOST.getData(),
//                    OtherBaseEnum.ALIBABA_CLOUD_SEND_MESSAGE_PATH.getData(),
//                    OtherBaseEnum.SUBMIT_POST.getData(),
//                    sendMessageHeader,
//                    sendMessageQuery,
//                    sendMessageBody);
//            if (response == null) {
//                Log.e(MsgEnum.ALIBABA_CLOUD_RESPONSE_NULL_EXCEPTION);
//                return Result.fail(MsgEnum.THIRD_PARTY_RESPONSE_ERROR);
//            }
//            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
//                Log.e(response.getFirstHeader("X-Ca-Error-Message").getValue());
//                return Result.fail(MsgEnum.THIRD_PARTY_RESPONSE_ERROR);
//            }
//            String string = EntityUtils.toString(response.getEntity());
//            if (string.isEmpty()) {
//                Log.e(MsgEnum.ALIBABA_CLOUD_RESPONSE_NULL_EXCEPTION);
//                return Result.fail(MsgEnum.THIRD_PARTY_RESPONSE_ERROR);
//            }
//            JSONObject jsonObject = JSONObject.parseObject(string);
//            if(jsonObject.getString("code").equals(OtherBaseEnum.ALIBABA_CLOUD_SEND_MESSAGE_SUCCESS_CODE.getData())){
//                UserVo userVo = new UserVo();
//                userVo.setPhoneNumber(phoneNumber);
//                userVo.setSmsCode(smsCode);
//                Redis.setLoginSmsCode(phoneNumber,smsCode);
//                return Result.success(userVo);
//            }
//            return Result.fail(jsonObject.getString("msg"));
//        } catch (Exception e) {
//            Log.e(e.getMessage());
//            return Result.fail(e.getMessage());
//        }
    }

    @Override
    public Result<UserVo> refresh(String refreshJwt) {
        String accessToken = jwtTokenUtil.refreshAccessToken(refreshJwt);
        if (CheckUtil.isEmpty(accessToken)) {
            return Result.fail(MsgEnum.REFRESH_JWT_EXPIRE, null);
        }
        UserVo UserVo = new UserVo();
        UserVo.setToken("Bearer " + accessToken);
        return Result.success(UserVo);
    }

    private User getUserForPhoneNumber(String phoneNumber) {
        CheckUtil.checkEmpty(phoneNumber, "phoneNumber");
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getPhoneNumber, phoneNumber)
                .eq(User::getStatus, BaseStateEnum.DELETE_STATE_ENABLE.getState());
        return userMapper.selectOne(lambdaQueryWrapper);

    }

}
