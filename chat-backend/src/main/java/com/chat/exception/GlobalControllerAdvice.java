package com.chat.exception;

import com.chat.utils.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yzz
 */
@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultVO errorHandler(Exception ex) {
        log.error("全局异常信息：{}", ex.toString());
        return new ResultVO("SYSTEM_ERROR", ex.getMessage());
    }

    /**
     * 拦截捕捉自定义异常 BusinessException.class
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public ResultVO myErrorHandler(BusinessException ex) {
        return new ResultVO(ex.getCode(), ex.getMessage());
    }

    /**
     * 参数错误
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultVO MethodArgumentNotValidHandler(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        List<ObjectError> errors = bindingResult.getAllErrors();
        String errorMsg = "";
        if (!CollectionUtils.isEmpty(errors)) {
            errorMsg = errors.get(0).getDefaultMessage();
        }
        final String field = bindingResult.getFieldError().getField();
        return new ResultVO(field.toUpperCase() + "_ERROR", errorMsg);
    }
}