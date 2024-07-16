package com.muxingzhe.check.utils.exception;

/**
 * @author kampf
 * @date 2019/12/30 下午3:42
 */
public class FileException extends RuntimeException{

    private final String msg;

    public FileException(String msg){
        super("");
        this.msg = msg;
    }

    @Override
    public String getMessage(){
        return this.msg;
    }
}
