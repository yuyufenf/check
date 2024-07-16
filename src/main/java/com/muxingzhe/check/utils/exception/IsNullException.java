package com.muxingzhe.check.utils.exception;

/**
 * 自定义空值错误，以便传递返回信息被异常抓取住
 * @author kampf
 * @date 2019/8/19 18:44
 */
public class IsNullException extends RuntimeException {

    private final String msg;

    public IsNullException(String msg){
        super("");
        this.msg = msg;
    }

    @Override
    public String getMessage(){
        return this.msg;
    }
}
