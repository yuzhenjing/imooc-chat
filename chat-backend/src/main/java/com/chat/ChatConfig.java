package com.chat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatConfig {
    @Bean
    public SpringUtil getSpingUtil() {
        return new SpringUtil();
    }
}
