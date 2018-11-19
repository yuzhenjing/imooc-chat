package com.chat.service;

import com.chat.netty.ChatMsg;
import com.chat.pojo.ChatUser;

import java.util.List;

/**
 * @Author yzj
 * @Date 2018/11/10 11:11
 * @Description 用户信息service
 */
public interface UserService {
    /**
     * 批量阅读信息
     *
     * @param msgIdList
     */
    void updateMsgSigned(List<String> msgIdList);

    /**
     * 保存消息到数据库 标识未未签收
     *
     * @param chatMsg
     * @return
     */
    String saveMsg(ChatMsg chatMsg);

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    ChatUser findUserByUserName(String username);

    /**
     * 添加用户
     *
     * @param chatUser
     */
    void addChatUser(ChatUser chatUser);

    /**
     * 根据用户名和密码查询用户
     *
     * @param username
     * @param password
     * @return
     */
    ChatUser findUserByUserNameAndPassword(String username, String password);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    ChatUser updateUserInfo(ChatUser user);
}
