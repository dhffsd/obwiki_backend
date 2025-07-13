package com.gec.obwiki;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@SpringBootTest
class ObwikiApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(11);
    }

    @Test
    public void generateCode() {
        String url = "jdbc:mysql://localhost:3306/obwiki?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";

        FastAutoGenerator.create(url, "root", "123456")
                .globalConfig(builder -> {
                    builder.author("cr") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("E://Code//"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.gec") // 设置父包名
                            .moduleName("obwiki") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "E://Code//")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("category,content,doc,ebook,ebook_snapshot,user"); // 设置需要生成的表名
                })
                .execute();
    }
}
