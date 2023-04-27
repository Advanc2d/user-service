package com.example.user.phm.controller;

import com.example.user.phm.vo.ConfigMessage;
import com.example.user.phm.dto.UserDto;
import com.example.user.phm.entity.UserEntity;
import com.example.user.phm.service.UserService;
import com.example.user.phm.vo.RequestUser;
import com.example.user.phm.vo.ResponseUser;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserApiController {

    @Value("${welcome.message}")
    private String message;

    private Environment env;
    private ConfigMessage cm;
    private ModelMapper modelMapper;
    private UserService userService;

    @Autowired
    public UserApiController(Environment env,ConfigMessage cm,
                             ModelMapper modelMapper, UserService userService) {
        this.env = env;
        this.cm = cm;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/health_check")
    public String status() {
        log.info(env.getProperty("welcome.message"));
        log.info(cm.getMessage());
        return this.message;
    }
    @PostMapping("/email/check/{email}")
    public String getUserEmailCheck(@RequestParam(value = "email", required=true) String email)  {
        log.info("getUserEmailCheck : {}", email);
        String result = "";
        try {
            boolean flag = userService.findByEmail(email);
            if (flag) {
                result = "full";
            }
            else {
                result = "empty";
            }
        }
        catch(Exception e) {
            e.getMessage();
        }
        return result;
    }

    @PostMapping
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user)  {
        log.info("createUser : {}", user);
        modelMapper.getConfiguration().setMatchingStrategy((MatchingStrategies.STRICT));

        UserDto userDto = modelMapper.map(user, UserDto.class);
        userService.createUser(userDto);
        ResponseUser responseUser = modelMapper.map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }
}
