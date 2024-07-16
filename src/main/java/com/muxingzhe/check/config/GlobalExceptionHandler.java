package com.muxingzhe.check.config;

import com.muxingzhe.check.utils.Result;
import com.muxingzhe.check.utils.exception.IsNullException;
import com.muxingzhe.check.utils.exception.PermissionDeniedException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 公用异常抓取类，用于后期自定义标签给页面返回
 * @author kampf
 * @date 2019/8/16 19:39
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 抓取非空校验异常并返回
     * @param e
     * @return
     */
    @ExceptionHandler(IsNullException.class)
    public Result getNullException(IsNullException e){
        log.debug(IsNullException.class + "被抓取");
        return Result.error(Result.SERVER_ERROR, e.getMessage());
    }

    /**
     * 抓取登录校验失败异常并返回
     * @param e
     * @return
     */
    @ExceptionHandler(PermissionDeniedException.class)
    public Result getPermissionException(PermissionDeniedException e){
        log.debug(PermissionDeniedException.class + "被抓取");
        return Result.error(Result.TOKEN_ERROR, e.getMessage());
    }
}
