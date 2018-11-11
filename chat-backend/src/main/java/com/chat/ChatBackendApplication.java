package com.chat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.chat.mapper")
@SpringBootApplication
public class ChatBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatBackendApplication.class, args);
    }
}
