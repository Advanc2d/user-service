package com.example.user.phm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
public class UserController {
    // @GetMapping("/login")
    // public String login() {
    //     log.info("login html");
    //     return "login";
    // }

    @GetMapping("/join")
    public String join() {
        log.info("join html");
        return "join";
    }
}