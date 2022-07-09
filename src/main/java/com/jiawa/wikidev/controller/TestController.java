package com.jiawa.wikidev.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${hello}")
    private String testhello;

    @GetMapping ("/hello")
    public String hello(){
        return "hello,my name is ..."+testhello;
    }
}
