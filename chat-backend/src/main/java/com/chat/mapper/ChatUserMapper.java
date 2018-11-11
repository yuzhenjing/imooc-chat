package com.chat.mapper;

import com.chat.pojo.ChatUser;
import org.apache.ibatis.annotations.Param;

public interface ChatUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(ChatUser record);

    int insertSelective(ChatUser record);

    ChatUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ChatUser record);

    int updateByPrimaryKey(ChatUser record);

    /**
     * 查询用户
     *
     * @param username
     * @param password
     * @return
     */
    ChatUser findUserByUserNameAndPassword(@Param("username") String username, @Param("password") String password);
}