package com.muxingzhe.check.main.service.impl;

import com.muxingzhe.check.main.entity.dto.TokenDTO;
import com.muxingzhe.check.main.entity.form.LoginForm;
import com.muxingzhe.check.main.entity.vo.LoginVO;
import com.muxingzhe.check.main.service.AdminService;
import com.muxingzhe.check.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author kampf
 * @version 1.0
 * @description:
 * @date 2024/6/12 10:34
 */
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    @Value("${config.token.instructions}")
    private String instructions;

    @Value("${config.token.timeout}")
    private int timeout;

    @Override
    public LoginVO login(LoginForm form) {
        boolean flag = Boolean.TRUE;

        while (!flag){
            return LoginVO.builder()
                    .status(Boolean.FALSE)
                    .msg("用户名或密码错误")
                    .build();
        }

        String token = TokenUtils.getToken(TokenDTO.builder()
                .key(instructions.getBytes())
                .userName(form.getUserName())
                .admin(Boolean.TRUE)
                .ExpiresTime(timeout)
                .build());

        return LoginVO.builder()
                .status(Boolean.TRUE)
                .token(token)
                .build();
    }
}
