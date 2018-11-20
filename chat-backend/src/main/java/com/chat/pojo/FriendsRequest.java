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

    private Date sendDateTime;

    private String acceptUserId;

    private Date acceptDateTime;

    private String requestMsg;

}
