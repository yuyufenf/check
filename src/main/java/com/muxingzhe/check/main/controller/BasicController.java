/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.muxingzhe.check.main.controller;

import cn.hutool.core.codec.Base64;
import com.muxingzhe.check.main.entity.User;
import com.muxingzhe.check.main.entity.form.CheckForm;
import com.muxingzhe.check.main.entity.form.LoginForm;
import com.muxingzhe.check.main.entity.vo.LoginVO;
import com.muxingzhe.check.main.service.AdminService;
import com.muxingzhe.check.main.service.HistoryService;
import com.muxingzhe.check.utils.IpUtils;
import com.muxingzhe.check.utils.Result;
import com.muxingzhe.check.utils.annotation.PassLogin;
import com.muxingzhe.check.utils.annotation.PassToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Slf4j
@RestController
@RequestMapping(value = "/basis")
@Api(tags = "常规开发接口")
@AllArgsConstructor
public class BasicController {

    private HistoryService historyService;

    private AdminService adminService;

    // http://127.0.0.1:8080/hello?name=lisi
    @GetMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        return "Hello " + name;
    }

    @PostMapping("/login")
    @ApiOperation(value = "管理员登录接口")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK!"),
            @ApiResponse(code = 500, message = "系统异常"),
            @ApiResponse(code = 503, message = "Token异常"),
            @ApiResponse(code = 504, message = "非法请求"),
            @ApiResponse(code = 505, message = "请求次数过多")})
    @PassToken
    @PassLogin
    public Result login(@RequestBody LoginForm loginForm, HttpServletRequest request){
        log.info("登陆表单：{}", loginForm);
        LoginVO vo = adminService.login(loginForm);
        if(vo.getStatus()){
            return Result.success(vo.getToken());
        } else {
            return Result.error(Result.BIND_ERROR, vo.getMsg());
        }
    }

    // http://127.0.0.1:8080/user
    @PostMapping("/check")
    @ApiOperation(value = "根据机器码增加机器校验")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK!"),
            @ApiResponse(code = 500, message = "系统异常"),
            @ApiResponse(code = 503, message = "Token异常"),
            @ApiResponse(code = 504, message = "非法请求"),
            @ApiResponse(code = 505, message = "请求次数过多")})
    public Result deviceCheck(@RequestBody CheckForm form, HttpServletRequest request) {
        historyService.saveCheckLog(form, IpUtils.getIpAddr(request));

        return Result.success( Base64.encode("is ok!", StandardCharsets.UTF_8), Boolean.TRUE, 1, "");
    }

    @ModelAttribute
    public void parseUser(@RequestParam(name = "name", defaultValue = "unknown user") String name
            , @RequestParam(name = "age", defaultValue = "12") Integer age, User user) {
        user.setUserName("zhangsan");
        user.setAge(18);
    }
}
