package com.chat.controller;

import com.chat.bo.UsersBO;
import com.chat.form.ChatUserForm;
import com.chat.pojo.ChatUser;
import com.chat.service.UserService;
import com.chat.utils.FastDFSClient;
import com.chat.utils.FileUtils;
import com.chat.utils.ResultVO;
import com.chat.utils.idworker.Sid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/register")
    public ResultVO register(@RequestBody @Valid ChatUserForm chatUserFrom) {
        log.info("用户请求信息：{}", chatUserFrom);
        ChatUser chatUser = userService.findUserByUserName(chatUserFrom.getUsername());
        if (chatUser == null) {
            chatUser = new ChatUser();
            BeanUtils.copyProperties(chatUserFrom, chatUser);
            chatUser.setId(Sid.nextShort());
            chatUser.setNickName(chatUser.getUsername());
            chatUser.setCreateTime(new Date());
            userService.addChatUser(chatUser);
        } else {
            chatUser = userService.findUserByUserNameAndPassword(chatUserFrom.getUsername(), chatUserFrom.getPassword());
            Assert.isNull(chatUser, "密码错误！");
        }
        return ResultVO.ok();
    }

    /**
     * @Description: 上传用户头像
     */
    @PostMapping("/uploadFaceBase64")

    public ResultVO<ChatUser> uploadFaceBase64(@RequestBody UsersBO userBO) throws Exception {

        // 获取前端传过来的base64字符串, 然后转换为文件对象再上传
        String base64Data = userBO.getFaceData();
        String userFacePath = "C:\\" + userBO.getUserId() + "userface64.png";
        FileUtils.base64ToFile(userFacePath, base64Data);

        // 上传文件到fastdfs
        MultipartFile faceFile = FileUtils.fileToMultipart(userFacePath);
        String url = fastDFSClient.uploadBase64(faceFile);
        System.out.println(url);

//		"dhawuidhwaiuh3u89u98432.png"
//		"dhawuidhwaiuh3u89u98432_80x80.png"

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

        return ResultVO.build(result);
    }

}
