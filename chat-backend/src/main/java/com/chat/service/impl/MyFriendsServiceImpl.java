package com.chat.service.impl;

import com.chat.mapper.MyFriendsMapper;
import com.chat.service.MyFriendsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MyFriendsServiceImpl implements MyFriendsService {


    @Resource
    private MyFriendsMapper myFriendsMapper;


    @Override
    public Boolean findFriendsById(String myId, String friendId) {
        int count = myFriendsMapper.findFriendsById(myId, friendId);
        return count == 0;
    }
}
