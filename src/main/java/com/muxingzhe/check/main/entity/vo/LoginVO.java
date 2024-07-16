package com.muxingzhe.check.main.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kampf
 * @version 1.0
 * @description:
 * @date 2024/6/12 10:55
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "登陆返回对象")
public class LoginVO {

    private Boolean status;

    private String token;

    private String msg;
}
