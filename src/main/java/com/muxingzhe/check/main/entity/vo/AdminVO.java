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
 * @date 2024/6/14 14:34
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "管理员信息返回对象")
public class AdminVO {

    private String id;

    private String userName;

    private String email;

    private String remark;

    private String createDate;

}
