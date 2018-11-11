package com.chat.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author yzz
 *  我的好友
 */
@Data
public class MyFriends {
    private String id;

    private String myUserId;

    private String friendsId;

    private Date createTime;
}