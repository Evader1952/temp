package com.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mp.mapper")
public class RedisTempApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisTempApplication.class, args);
    }

}
