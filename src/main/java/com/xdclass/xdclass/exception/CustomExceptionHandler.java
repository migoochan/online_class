package com.xdclass.xdclass.exception;

import com.xdclass.xdclass.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理类
 */
@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData handle(Exception e) {
        log.error("[异常信息:{}]", e.getStackTrace());
        if (e instanceof XDException) {
            XDException e1 = (XDException) e;
            return JsonData.buildError(e1.getCode(), e1.getMsg());
        } else {
            return JsonData.buildError("全局异常, 未知错误");
        }
    }
}
