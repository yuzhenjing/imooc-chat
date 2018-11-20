package com.chat.service.impl;

import com.chat.mapper.FriendsRequestMapper;
import com.chat.pojo.FriendsRequest;
import com.chat.service.FriendsRequestService;
import com.chat.utils.idworker.Sid;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class FriendsRequestServiceImpl implements FriendsRequestService {

    @Resource
    private FriendsRequestMapper friendsRequestMapper;

    @Override
    public void addFriends(String myUserId, String friendId) {
        FriendsRequest friendsRequest = new FriendsRequest();
        friendsRequest.setId(Sid.nextShort());
        friendsRequest.setSendUserId(myUserId);
        friendsRequest.setAcceptUserId(friendId);
        friendsRequest.setSendDateTime(new Date());
        friendsRequestMapper.insert(friendsRequest);
    }
}
