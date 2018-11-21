package com.chat.service.impl;

import com.chat.mapper.ChatMsgMapper;
import com.chat.pojo.ChatMsg;
import com.chat.service.ChatMsgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChatMsgServiceImpl implements ChatMsgService {

    @Resource
    private ChatMsgMapper chatMsgMapper;


    @Override
    public List<ChatMsg> findChatMsgByUnread(String acceptUserId) {

        return chatMsgMapper.findChatMsgByUnread(acceptUserId);
    }
}
