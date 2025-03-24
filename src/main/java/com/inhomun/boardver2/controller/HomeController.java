package com.inhomun.boardver2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class HomeController {
    @GetMapping("/")
    public String main() {
        System.out.println("HomeController.index");
        return "main";
    }
    @GetMapping("/index")
    public String index() {
        System.out.println("HomeController.index");
        return "index";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
