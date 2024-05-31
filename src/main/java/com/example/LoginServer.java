package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.example.mapper")
@SpringBootApplication
@EnableScheduling
public class LoginServer {

    public static void main(String[] args) {
        SpringApplication.run(LoginServer.class, args);
    }
}
