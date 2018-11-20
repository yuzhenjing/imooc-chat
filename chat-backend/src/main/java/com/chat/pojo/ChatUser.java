package com.chat.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
 @NoArgsConstructor
@Data
 @AllArgsConstructor
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
