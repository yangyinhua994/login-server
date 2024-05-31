package com.example.utils;

import java.util.Random;

public class SmsCodeUtil {

    public static String generateSmsCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10)); // 生成0-9的随机数字
        }
        return code.toString();
    }

}
