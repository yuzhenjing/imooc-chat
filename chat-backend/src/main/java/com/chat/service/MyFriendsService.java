package com.chat.service;

public interface MyFriendsService {
    /**
     * 查询两个用户是否是好友
     *
     * @param myId
     * @param friendId
     * @return
     */
    Boolean findFriendsById(String myId, String friendId);
}
