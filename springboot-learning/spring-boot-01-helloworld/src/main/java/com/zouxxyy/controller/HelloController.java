package com.zouxxyy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @Value("${person.last-name}")
    private String name;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World! , " + name;
    }
}
