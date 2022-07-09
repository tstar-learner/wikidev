package com.jiawa.wikidev;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
@MapperScan("com.jiawa.wikidev.mapper")
public class WikidevApplication {

    private static final Logger LOG=  LoggerFactory.getLogger(WikidevApplication.class);

    public static void main(String[] args) {
        SpringApplication app=new SpringApplication(WikidevApplication.class);
        Environment env=app.run(args).getEnvironment();
        LOG.info("启动成功！！");
        LOG.info("地址：\thttp://127.0.0.1:{}",env.getProperty("server.port"));
    }

}
