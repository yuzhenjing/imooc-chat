package com.chat.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author yzj
 * @Date 2018/11/10 16:57
 * @Description 自定义异常
 */
@Data
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    private String code;
    private String msg;

}