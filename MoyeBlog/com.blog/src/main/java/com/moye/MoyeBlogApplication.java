package com.moye;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.moye.mapper")
public class MoyeBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(MoyeBlogApplication.class,args);
    }
}
