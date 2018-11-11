package com.chat.controller;

import com.chat.form.ChatUserForm;
import com.chat.pojo.ChatUser;
import com.chat.service.UserService;
import com.chat.utils.ResultVO;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @author yzz
 * 用户
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public ResultVO register(@RequestBody @Valid ChatUserForm chatUserFrom) {
        log.info("用户请求信息：{}", chatUserFrom);
        ChatUser chatUser = userService.findUserByUserName(chatUserFrom.getUsername());
        if (chatUser == null) {
            chatUser = new ChatUser();
            BeanUtils.copyProperties(chatUserFrom, chatUser);
            chatUser.setId(UUID.randomUUID().toString());
            chatUser.setNickName(chatUser.getUsername());
            chatUser.setCreateTime(new Date());
            userService.addChatUser(chatUser);
        } else {
            chatUser = userService.findUserByUserNameAndPassword(chatUserFrom.getUsername(), chatUserFrom.getPassword());
            Assert.isNull(chatUser, "密码错误！");
        }
        return ResultVO.ok();
    }


}
