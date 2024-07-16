package com.muxingzhe.check.main.controller;

import com.muxingzhe.check.main.entity.form.AdminForm;
import com.muxingzhe.check.main.entity.vo.AdminVO;
import com.muxingzhe.check.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kampf
 * @version 1.0
 * @description:
 * @date 2024/6/14 14:01
 */
@Slf4j
@RestController
@RequestMapping(value = "/admin")
@Api(tags = "管理功能接口")
@AllArgsConstructor
public class AdminController {

    @GetMapping("/adminList")
    @ApiOperation(value = "查询管理员信息")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK!"),
            @ApiResponse(code = 500, message = "系统异常"),
            @ApiResponse(code = 503, message = "Token异常"),
            @ApiResponse(code = 504, message = "非法请求"),
            @ApiResponse(code = 505, message = "请求次数过多")})
    public Result adminList(@RequestParam("select") String select, @RequestParam("input") String input){

        List<AdminVO> list = new ArrayList<>();
        list.add(AdminVO.builder()
                .id("1")
                .userName("测试1")
                .email("123@gmail.com")
                .remark("备注说明1")
                .createDate("2024-06-13")
                .build());
        list.add(AdminVO.builder()
                .id("2")
                .userName("测试2")
                .email("456@gmail.com")
                .remark("备注说明2")
                .createDate("2024-06-14")
                .build());

        return Result.success(list);
    }

    @PostMapping("/adminAdd")
    @ApiOperation(value = "新增管理员信息")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK!"),
            @ApiResponse(code = 500, message = "系统异常"),
            @ApiResponse(code = 503, message = "Token异常"),
            @ApiResponse(code = 504, message = "非法请求"),
            @ApiResponse(code = 505, message = "请求次数过多")})
    public Result adminAdd(@RequestBody AdminForm adminForm){

        log.info("新增管理员信息：{}", adminForm);

        return Result.success();
    }
}
