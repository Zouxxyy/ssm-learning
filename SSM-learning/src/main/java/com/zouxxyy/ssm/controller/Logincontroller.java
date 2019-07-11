package com.zouxxyy.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class Logincontroller {

    // 登陆
    @RequestMapping("/login")
    public String login(HttpSession session, String username, String password) throws Exception {

        // 在session中保存

        session.setAttribute("username", username);

        return "redirect:/items/queryItems.action";
    }


    // 退出
    @RequestMapping("/logout")
    public String logout(HttpSession session) throws Exception {
        session.invalidate();
        return "redirect:/items/queryItems.action";
    }
}
