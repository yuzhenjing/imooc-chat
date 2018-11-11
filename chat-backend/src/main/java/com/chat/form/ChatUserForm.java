package com.chat.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author yzj
 * @Date 2018/11/10 16:46
 * @Description 用户注册
 */
@Data
public class ChatUserForm {

    @NotNull(message = "用户名不能为空")
    @Length(max = 20, min = 5, message = "用户名应在5-20位之间")
    private String username;
    @NotNull(message = "密码不能为空")
    private String password;

    private String cid;
}
