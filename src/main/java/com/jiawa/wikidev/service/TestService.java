package com.jiawa.wikidev.service;

import com.jiawa.wikidev.domain.Test;
import com.jiawa.wikidev.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {

    @Resource
    public TestMapper testMapper;

    public List<Test> list(){
        return testMapper.list();
    }
}
