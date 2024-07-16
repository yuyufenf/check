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
 * @date 2024/5/28 5:16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "识别码请求表单")
public class CheckForm implements Serializable {

    private static final long serialVersionUID = 4217498159099367989L;

    @ApiModelProperty(value = "识别码", required = true)
    private String id;

    @ApiModelProperty(value = "识别信息", required = true)
    private String msg;
}
