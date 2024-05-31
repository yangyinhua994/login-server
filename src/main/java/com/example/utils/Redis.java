package com.example.utils;

import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class Redis {

    private static RedisTemplate<String, User> userRedisTemplate;
    private static RedisTemplate<String, String> stringRedisTemplate;

//    登录验证码失效时间(五分钟)
    private static Long PHONE_NUMBER_SMS_CODE_EXPIRATION_SECONDS = (long) (60 * 5);

//    分布式redis锁默认释放时间(三秒)
    private static final long LOCK_DEFAULT_EXPIRATION_SECONDS = 3;

//    redis储存用户信息key的前缀
    private static final String USER_KEY_PREFIX = "user:";

//    redis储存短信登录key的前缀
    private static final String SMS_LOGIN_PHONE_KEY_PREFIX = "smsLoginPhone:";

    @Autowired
    public Redis(RedisTemplate<String, User> userRedisTemplate, RedisTemplate<String, String> stringRedisTemplate) {
        Redis.userRedisTemplate = userRedisTemplate;
        Redis.stringRedisTemplate = stringRedisTemplate;
    }

    public static User getUser(String key) {
        //        开发前期不开启,因为会出现很多数据问题,业务逻辑基本完成后在考虑是否启动redis
//        return userRedisTemplate.opsForValue().get(key);
        return null;
    }

    public static User getUser(Long userId) {
        try {
            return getUser(USER_KEY_PREFIX + userId);
        }catch (Exception e){
            return null;
        }

    }

    public static Boolean deleteUser(String userKey){
        return userRedisTemplate.delete(userKey);
    }
    public static void setUser(String key, User user) {
        //        开发前期不开启,因为会出现很多数据问题,业务逻辑基本完成后在考虑是否启动redis
//        userRedisTemplate.opsForValue().set(key, user);
    }

    public static void setUser(Long id, User user) {
        setUser(USER_KEY_PREFIX + id, user);
    }

    public static boolean setNX(String lockKey, User value, long expireTimeInSeconds) {
        return Boolean.TRUE.equals(userRedisTemplate.opsForValue().setIfAbsent(lockKey, value, expireTimeInSeconds, TimeUnit.SECONDS));
    }

    public static boolean setNX(String lockKey, User value) {
        return Boolean.TRUE.equals(userRedisTemplate.opsForValue().setIfAbsent(lockKey, value, LOCK_DEFAULT_EXPIRATION_SECONDS, TimeUnit.SECONDS));
    }

    public static void releaseLock(String lockKey){
        userRedisTemplate.delete(lockKey);
    }

    public static String getLoginSmsCode(String phoneNumber) {
        return stringRedisTemplate.opsForValue().get(SMS_LOGIN_PHONE_KEY_PREFIX + phoneNumber);
    }

    public static void setLoginSmsCode(String phoneNumber, String smsCode) {
        stringRedisTemplate.opsForValue().set(SMS_LOGIN_PHONE_KEY_PREFIX + phoneNumber, smsCode, PHONE_NUMBER_SMS_CODE_EXPIRATION_SECONDS, TimeUnit.SECONDS);
    }

    public static void deleteLoginSmsCode(String phoneNumber) {
        stringRedisTemplate.delete(SMS_LOGIN_PHONE_KEY_PREFIX + phoneNumber);

    }



}
