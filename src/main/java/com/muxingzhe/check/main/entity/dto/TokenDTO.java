package com.muxingzhe.check.main.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kampf
 * @version 1.0
 * @description:
 * @date 2024/6/12 14:47
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "token创建对象")
public class TokenDTO {

    private byte[] key;

    private String userName;

    private Boolean admin;

    /*token存活时间，（min）*/
    private int ExpiresTime;
}
