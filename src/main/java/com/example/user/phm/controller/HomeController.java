package com.example.user.phm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sound.midi.SysexMessage;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String home(Model model) {
        // 컨트롤러 로직
        System.out.println("z");
        return "home/home"; // 뷰 이름
    }

}
