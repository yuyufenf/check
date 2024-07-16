package com.muxingzhe.check.utils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 跳过token验证（token类似session或cookie）
 * @author kampf
 * @date 2019/8/21 14:44
 */
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface PassToken {

    /**
     * 使用标签默认跳过
     * @return
     */
    boolean required() default true;
}
