package com.chat.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author yzj
 * @Date 2018/11/10 16:37
 * @Description 封装统一返回对象
 */
@Data
@AllArgsConstructor
public class ResultVO<T> {

    private String code;

    private String msg;

    private T date;

    public static ResultVO ok() {
        return new ResultVO<>("SUCCESS", "成功", null);
    }
    public static ResultVO build(Object o) {
        return new ResultVO<>("SUCCESS", "成功", o);
    }

    public ResultVO(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
