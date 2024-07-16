package com.muxingzhe.check.utils;

import cn.hutool.jwt.JWT;
import com.muxingzhe.check.main.entity.User;
import com.muxingzhe.check.main.entity.dto.TokenDTO;

import java.util.Date;

/**
 * @author kampf
 * @date 2020/1/3 下午2:47
 */
public class TokenUtils {

    public static String getToken(TokenDTO tokenDTO) {
        long currentTime = System.currentTimeMillis();
        Date loginTime = new Date(currentTime);
        if(tokenDTO.getExpiresTime() <= 1){
            tokenDTO.setExpiresTime(1);
        }
        Date validTime = new Date(currentTime + tokenDTO.getExpiresTime() * 60 * 1000);

        return JWT.create()
                .setPayload("userName", tokenDTO.getUserName())
                .setIssuedAt(loginTime).setExpiresAt(validTime)
                .setKey(tokenDTO.getKey())
                .sign();
    }
}
