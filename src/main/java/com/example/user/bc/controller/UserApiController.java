package com.example.user.bc.controller;

import com.example.user.bc.service.UserService;
import com.example.user.bc.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins="*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class UserApiController {

    private final UserService userService;

    // 생성자를 이용한 주입
    @Autowired
    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/info")
    public String getUserInfo() {
        return "";
    }

    @PostMapping("/user/check-email")
    public ResponseEntity<UserEntity> checkEmail(@RequestBody UserEntity user) throws Exception {

        Optional<UserEntity> userInfo = userService.findUserByEmail(user.getEmail());

        if (userInfo.isPresent()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/user/check-login")
    public ResponseEntity<HttpStatus> login(@RequestBody UserEntity user) throws Exception {

        Optional<UserEntity> userInfo = userService.findUserByEmail(user.getEmail());

        if (userInfo.isPresent() && userInfo.get().getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
