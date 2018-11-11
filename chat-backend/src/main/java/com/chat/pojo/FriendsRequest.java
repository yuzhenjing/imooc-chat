package com.chat.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author yzz
 */
@Data
public class FriendsRequest {
    private String id;

    private String sendUserId;

    private String sendDateTime;

    private String acceptUserId;

    private Date requestDateTime;

    private String requestMsg;

}