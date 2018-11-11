package com.chat.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class ChatUser {
    private String id;

    private String username;

    private String password;

    private String faceImage;

    private String faceImageBig;

    private String nickName;

    private String qrcode;

    private String cid;

    private Date createTime;
}