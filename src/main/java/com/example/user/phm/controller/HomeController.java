package com.example.user.phm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sound.midi.SysexMessage;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @GetMapping
    public String home(Model model) {
        // 컨트롤러 로직
        logger.trace("Trace level 테스트");
        logger.debug("Debug level 테스트");
        logger.info("Info level 테스트");
        logger.warn("Warn level 테스트");
        logger.error("Error level 테스트");

        return "home/home"; // 뷰 이름
    }

}
