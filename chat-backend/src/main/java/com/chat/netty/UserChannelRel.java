package com.chat.netty;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * @Description: 用户id和channel的关联关系处理
 */
@Slf4j
public class UserChannelRel {

    private static HashMap<String, Channel> manager = new HashMap<>();

    public static void put(String senderId, Channel channel) {
        manager.put(senderId, channel);
    }

    public static Channel get(String senderId) {
        return manager.get(senderId);
    }

    public static void output() {
        manager.entrySet().forEach(entry -> log.info("UserId:{}, ChannelId:{}", entry.getKey(), entry.getValue().id().asLongText()));
    }
}
