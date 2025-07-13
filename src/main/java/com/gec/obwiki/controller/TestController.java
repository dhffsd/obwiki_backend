package com.gec.obwiki.controller;

import cn.hutool.http.HtmlUtil;
import com.gec.obwiki.req.DemoReq;
import com.gec.obwiki.resp.CommonResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/test")
@Api("测试Web接口") // Swagger声明类加@Api注解, 并说明
public class TestController {

    @PostMapping("/sayHello")
    @ApiOperation("最简单的测试方法") // Swagger声明方法添加@ApiOperation注解,并说明
    public CommonResp sayHello(@Valid @RequestBody DemoReq demoReq) {
        // 对接收到的数据进行XSS过滤
        if (demoReq.getName() != null) {
            demoReq.setName(HtmlUtil.filter(demoReq.getName()));
        }
        if (demoReq.getPhone() != null) {
            demoReq.setPhone(HtmlUtil.filter(demoReq.getPhone()));
        }
        if (demoReq.getPassword() != null) {
            demoReq.setPassword(HtmlUtil.filter(demoReq.getPassword()));
        }
        
        return new CommonResp(true, "查询成功", demoReq);
    }
} 