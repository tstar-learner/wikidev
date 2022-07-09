package com.jiawa.wikidev.controller;

import com.jiawa.wikidev.domain.Test;
import com.jiawa.wikidev.service.TestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {

    @Resource
    public TestService testService;

    @GetMapping ("/hello")
    public String hello(){
        return "hello,my name is ...";
    }

    @GetMapping("/test/list")
    public List<Test> list(){
        return testService.list();
    }
}
