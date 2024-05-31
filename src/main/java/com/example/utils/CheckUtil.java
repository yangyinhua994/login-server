package com.example.utils;

import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil {

    public static void checkEmpty(Long l, String msg) {
        if (l == null || l == 0L) {
            throw new NullPointerException(msg + " cannot be null");
        }

    }

    public static boolean checkEmpty(String... args) {
        for (String arg : args) {
            if (ObjectUtils.isEmpty(arg)) {
                return true;
            }
        }
        return false;

    }

    public static void checkEmptyAll(String msg, String... args) {
        for (String arg : args) {
            if (arg == null || arg.isEmpty()) {
                throw new NullPointerException(msg + " cannot be null");
            }
        }

    }

    public static void checkEmptyAll(String msg, Object... args) {
        if (isEmpty(args)){
            throw new NullPointerException(msg + " cannot be null");
        }

    }

    public static void checkEmpty(String str, String msg) {
        if (str == null || str.isEmpty()) {
            throw new NullPointerException(msg + " cannot be null");
        }

    }

    public static boolean isEmpty(Long l) {
        return l == null || l == 0L;

    }

    public static boolean isEmpty(Object... args) {
        if (args == null || args.length == 0) {
            return true;
        }
        boolean b = false;
        for (Object arg : args) {
            if (arg instanceof String) {
                b = arg.equals("");
            } else if (arg instanceof Long) {
                b = arg.equals(0L);
            } else if (arg instanceof Integer) {
                b = arg.equals(0);
            } else if (arg instanceof MultipartFile) {
                b = ((MultipartFile) arg).isEmpty();
            } else if (arg instanceof List) {
                b = ((List<?>) arg).isEmpty();
            } else {
                b = ObjectUtils.isEmpty(arg);
            }
            if (b) {
                return b;
            }
        }
        return b;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();

    }

    public static boolean isPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }
        String regex = "^1[0-9]{10}$"; // 手机号码正则表达式
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();

    }

    public static boolean isPhoneNumber(List<String> phoneNumbers) {
        if (phoneNumbers == null || phoneNumbers.isEmpty()) {
            return false;
        }
        for (String phoneNumber : phoneNumbers) {
            if (!isPhoneNumber(phoneNumber)){
                return false;
            }
        }
        return true;
    }

}
