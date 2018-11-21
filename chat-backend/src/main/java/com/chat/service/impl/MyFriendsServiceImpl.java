package com.chat.service.impl;

import com.chat.mapper.MyFriendsMapper;
import com.chat.pojo.MyFriends;
import com.chat.service.MyFriendsService;
import com.chat.vo.MyFriendsVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MyFriendsServiceImpl implements MyFriendsService {


    @Resource
    private MyFriendsMapper myFriendsMapper;


    @Override
    public Boolean findFriendsById(String myId, String friendId) {
        int count = myFriendsMapper.findFriendsById(myId, friendId);
        return count == 0;
    }

    @Override
    public List<MyFriendsVO> queryMyfriends(String userId) {
        return myFriendsMapper.queryMyfriends(userId);
    }
}
