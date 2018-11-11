package com.chat.service;

import com.chat.mapper.ChatUserMapper;
import com.chat.netty.ChatMsg;
import com.chat.pojo.ChatUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author yzj
 * @Date 2018/11/10 16:52
 * @Description TODO
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private ChatUserMapper userMapper;


    @Override
    public void updateMsgSigned(List<String> msgIdList) {

    }

    @Override
    public String saveMsg(ChatMsg chatMsg) {
        return null;
    }

    @Override
    public ChatUser findUserByUserName(String username) {
        return userMapper.findUserByUserNameAndPassword(username, null);
    }

    @Override
    public void addChatUser(ChatUser chatUser) {
        userMapper.insert(chatUser);
    }

    @Override
    public ChatUser findUserByUserNameAndPassword(String username, String password) {
        return userMapper.findUserByUserNameAndPassword(username, password);
    }
}
