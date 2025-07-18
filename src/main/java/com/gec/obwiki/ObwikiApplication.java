package com.gec.obwiki;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

@ServletComponentScan
@SpringBootApplication
public class ObwikiApplication {
    // 使用SLF4j框架创建日志记录器
    private static final Logger LOG = LoggerFactory.getLogger(ObwikiApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ObwikiApplication.class);
        ConfigurableEnvironment env = app.run(args).getEnvironment();
        LOG.info("项目启动成功!");
        LOG.info("地址:\thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }
}
