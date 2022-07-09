package com.jiawa.wikidev.service;

import com.jiawa.wikidev.domain.Demo;
import com.jiawa.wikidev.mapper.DemoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DemoService {

    @Resource
    public DemoMapper demoMapper;

    public List<Demo> list(){
        return demoMapper.selectByExample(null);
    }
}
