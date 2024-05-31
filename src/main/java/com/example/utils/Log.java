package com.example.utils;

import com.example.msg.MsgEnum;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log {

    private static final Logger logger = LogManager.getLogger(Log.class);

    public static void i(String log){
        logger.info(log);
    }

    public static void e(String log){
        logger.error(log);
    }

    public static void e(MsgEnum msgEnum){
        e(msgEnum.getMsg());
    }

    public static void d(String log){
        logger.debug(log);
    }

}
