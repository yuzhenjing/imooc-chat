package com.chat.controller;

import com.chat.bo.UsersBO;
import com.chat.form.ChatUserForm;
import com.chat.pojo.ChatUser;
import com.chat.service.FriendsRequestService;
import com.chat.service.MyFriendsService;
import com.chat.service.UserService;
import com.chat.utils.FastDFSClient;
import com.chat.utils.FileUtils;
import com.chat.utils.QRCodeUtil;
import com.chat.utils.ResultVO;
import com.chat.utils.idworker.Sid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

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
    @Resource
    private FastDFSClient fastDFSClient;

    @Autowired
    private MyFriendsService myFriendsService;

    @Autowired
    private FriendsRequestService friendsRequestService;


    @PostMapping("/register")
    public ResultVO register(@RequestBody @Valid ChatUserForm chatUserFrom) throws Exception {
        log.info("用户请求信息：{}", chatUserFrom);
        ChatUser chatUser = userService.findUserByUserName(chatUserFrom.getUsername());
        if (chatUser == null) {
            chatUser = new ChatUser();
            BeanUtils.copyProperties(chatUserFrom, chatUser);
            chatUser.setId(Sid.nextShort());
            String path = QRCodeUtil.encode(chatUser.getUsername(), "d:/", true);
            final String codeUrl = fastDFSClient.uploadFile("d:/" + path);
            chatUser.setQrcode(codeUrl);
            chatUser.setNickName(chatUser.getUsername());
            chatUser.setCreateTime(new Date());
            userService.addChatUser(chatUser);
        } else {
            chatUser = userService.findUserByUserNameAndPassword(chatUserFrom.getUsername(), chatUserFrom.getPassword());
            Assert.notNull(chatUser, "密码错误！");
        }
        return ResultVO.build(chatUser);
    }

    /**
     * @Description: 上传用户头像
     */
    @PostMapping("/uploadFaceBase64")
    public ResultVO<ChatUser> uploadFaceBase64(@RequestBody UsersBO userBO) throws Exception {
        final byte[] bytes = FileUtils.stringTobase64(userBO.getFaceData());
        String url = fastDFSClient.uploadBase64(bytes);
        // 获取缩略图的url
        String thump = "_80x80.";
        String arr[] = url.split("\\.");
        String thumpImgUrl = arr[0] + thump + arr[1];
        // 更细用户头像
        ChatUser user = new ChatUser();
        user.setId(userBO.getUserId());
        user.setFaceImage(thumpImgUrl);
        user.setFaceImageBig(url);
        ChatUser result = userService.updateUserInfo(user);
        log.info("ResultVO.build(result):{}", ResultVO.build(result));
        return ResultVO.build(result);
    }

    /**
     * @Description: 设置用户昵称
     */
    @PostMapping("/setNickname")
    public ResultVO<ChatUser> setNickname(@RequestBody UsersBO userBO) throws Exception {
        ChatUser chatUser = userService.findUserByUserId(userBO.getUserId());
        if (chatUser != null) {
            chatUser.setNickName(userBO.getNickname());
            userService.updateUserInfo(chatUser);
        }
        return ResultVO.build(chatUser);
    }

    @PostMapping("/searchFrends")
    public ResultVO<ChatUser> searchFrends(String myUserId, String friendUsername) throws Exception {
        final ChatUser friendUser = userService.findUserByUserName(friendUsername);
        Assert.notNull(friendUser, "查无此人");
        Assert.isTrue(!myUserId.equals(friendUser.getId()), "不需要添加自己为好友");
        final Boolean isFriend = myFriendsService.findFriendsById(myUserId, friendUser.getId());
        Assert.isTrue(isFriend, "你们已经是好友了");
        return ResultVO.build(friendUser);
    }

    @PostMapping("/addFriendRequest")
    public ResultVO<ChatUser> addFriendRequest(String myUserId, String friendUsername) throws Exception {
        ResultVO<ChatUser> friendUser = searchFrends(myUserId, friendUsername);
        friendsRequestService.addFriends(myUserId, friendUser.getData().getId());
        return ResultVO.ok();
    }


}
