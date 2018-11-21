package com.chat.service;

import com.chat.pojo.MyFriends;
import com.chat.vo.MyFriendsVO;

import java.util.List;

public interface MyFriendsService {
    /**
     * 查询两个用户是否是好友
     *
     * @param myId
     * @param friendId
     * @return
     */
    Boolean findFriendsById(String myId, String friendId);

    /**
     * 查询我的好友
     *
     * @param userId
     * @return
     */
    List<MyFriendsVO> queryMyfriends(String userId);
}
