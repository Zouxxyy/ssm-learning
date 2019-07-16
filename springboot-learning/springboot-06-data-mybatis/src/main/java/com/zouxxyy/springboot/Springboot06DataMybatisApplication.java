package com.zouxxyy.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.zouxxyy.springboot.mapper")
@SpringBootApplication
public class Springboot06DataMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot06DataMybatisApplication.class, args);
    }

}
