package com.chat.service;

import com.chat.pojo.ChatMsg;

import java.util.List;

public interface ChatMsgService {
    /**
     * 查询未读消息
     *
     * @param acceptUserId
     * @return
     */
    List<ChatMsg> findChatMsgByUnread(String acceptUserId);


}
