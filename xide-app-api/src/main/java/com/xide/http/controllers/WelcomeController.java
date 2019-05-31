package com.xide.http.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Api(tags = {"9-1.测试接口"})
public class WelcomeController {

    @GetMapping(path = "")
    @ApiOperation(value = "启动测试")
    public String welcome(){
        return "hi,this is xide-api project at re environment";
    }
}
