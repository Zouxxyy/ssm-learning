package com.zouxxyy.springboot.controller;

import com.zouxxyy.springboot.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {


//    @ResponseBody
//    @ExceptionHandler(UserNotExistException.class)
//    public Map<String, Object> handeException(Exception e) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", "user.noexist");
//        map.put("message", e.getMessage());
//        return map;
//    }

    // 自适应错误
    @ExceptionHandler(UserNotExistException.class)
    public String handeException(Exception e, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        // 传人错误状态吗
        request.setAttribute("javax.servlet.error.status_code", 400);
        map.put("code", "user.noexist");
        map.put("message", e.getMessage());
        request.setAttribute("ext",map);
        return "forward:/error";
    }

}
