package com.gec.obwiki.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptTest {
    public static void main(String[] args) {
        String raw = "123456";
        String encoded = "$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 1. 测试明文和数据库密文是否匹配
        System.out.println("BCrypt 校验结果: " + encoder.matches(raw, encoded));
        // 2. 生成新的密文
        String newEncoded = encoder.encode(raw);
        System.out.println("新生成的密文: " + newEncoded);
        // 3. 用新密文再校验一次
        System.out.println("新密文校验: " + encoder.matches(raw, newEncoded));
    }
} 