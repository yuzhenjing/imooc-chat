package com.chat.service;

/**
 * 好友请求表
 */
public interface FriendsRequestService {
    /**
     * 添加好友请求
     *
     * @param myUserId
     * @param id
     */
    void addFriends(String myUserId, String id);
}
