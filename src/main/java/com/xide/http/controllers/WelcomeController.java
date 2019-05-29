package com.xide.http.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 启动测试接口
 */
@RestController
@RequestMapping("/")
public class WelcomeController {

    @GetMapping(path = "")
    public String welcome(){
        return "hi,this is xide-api project at re environment";
    }
}
