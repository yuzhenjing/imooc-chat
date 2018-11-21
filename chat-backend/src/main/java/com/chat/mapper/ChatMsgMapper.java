package com.chat.mapper;

import com.chat.pojo.ChatMsg;

import java.util.List;

public interface ChatMsgMapper {
    int deleteByPrimaryKey(String id);

    int insert(ChatMsg record);

    int insertSelective(ChatMsg record);

    ChatMsg selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ChatMsg record);

    int updateByPrimaryKey(ChatMsg record);

    /**
     * 查询未读消息
     *
     * @param acceptUserId
     * @return
     */
    List<ChatMsg> findChatMsgByUnread(String acceptUserId);
}
