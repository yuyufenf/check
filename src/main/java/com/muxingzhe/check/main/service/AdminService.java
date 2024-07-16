package com.muxingzhe.check.main.service;

import com.muxingzhe.check.main.entity.form.LoginForm;
import com.muxingzhe.check.main.entity.vo.LoginVO;

/**
 * @author kampf
 * @version 1.0
 * @description:
 * @date 2024/6/12 10:34
 */
public interface AdminService {

    LoginVO login(LoginForm form);
}
