package com.yuan.oa_web;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.yuan")
@MapperScan(value = {"com.yuan.oa_dao.dao"})
public class OaWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(OaWebApplication.class, args);
    }

}
