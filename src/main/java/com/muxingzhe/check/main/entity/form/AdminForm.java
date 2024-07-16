package com.muxingzhe.check.main.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author kampf
 * @version 1.0
 * @description:
 * @date 2024/7/9 18:42
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "管理员信息表单")
public class AdminForm implements Serializable {

    private static final long serialVersionUID = -1907430396915792792L;

    @ApiModelProperty(value = "管理员名称", required = true)
    private String name;

    @ApiModelProperty(value = "登陆密码", required = true)
    private String password;

    @ApiModelProperty(value = "管理员权限")
    private String role;
}
