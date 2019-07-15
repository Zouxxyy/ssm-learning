package com.zouxxyy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 说明这是一个springboot应用
@SpringBootApplication
public class HelloWorldMainApplication {
    public static void main(String[] args) {

        // springboot应用启动
        SpringApplication.run(HelloWorldMainApplication.class, args);
    }
}
