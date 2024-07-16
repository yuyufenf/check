package com.muxingzhe.check.main.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kampf
 * @version 1.0
 * @description:
 * @date 2024/6/12 10:22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "登陆表单")
public class LoginForm {

    @ApiModelProperty(required = true, value="用户名")
    private String userName;

    @ApiModelProperty(required = true, value="密码")
    private String password;
}
