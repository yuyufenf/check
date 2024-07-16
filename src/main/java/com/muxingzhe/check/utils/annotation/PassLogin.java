package com.muxingzhe.check.utils.annotation;

/**
 * 跳过登陆验证标签
 * @author kampf
 * @date 2019/8/21 16:06
 */
public @interface PassLogin {

    /**
     * 使用标签默认跳过
     * @return
     */
    boolean required() default true;
}
