package com.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mp.mapper")
public class SbMpTempApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbMpTempApplication.class, args);
    }

}
