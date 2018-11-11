package com.chat.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author yzz
 * 聊天记录
 */
@Data
public class ChatMsg {
    private String id;

    private String sendUserId;

    private String acceptUserId;

    private String sendMsg;

    private Integer signFlag;

    private Date createTime;
}