package com.muxingzhe.check.utils.exception;

/**
 * 自定义无权限异常
 * @author kampf
 * @date 2019/8/21 16:49
 */
public class PermissionDeniedException extends RuntimeException {

    private final String msg;

    public PermissionDeniedException(String msg){
        super("");
        this.msg = msg;
    }

    @Override
    public String getMessage(){
        return this.msg;
    }
}
