package com.example.user.phm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/join")
    public String index() {
        log.info("index html");
        return "join";
    }
}
