package com.example.user.phm.controller;

import com.example.user.phm.vo.ConfigMessage;
import com.example.user.phm.dto.UserDto;
import com.example.user.phm.entity.UserEntity;
import com.example.user.phm.service.UserService;
import com.example.user.phm.vo.RequestUser;
import com.example.user.phm.vo.ResponseUser;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

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
@RequestMapping("/")
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
    @GetMapping("/message")
    public String message(@RequestHeader("user-request") String header) {
        log.info(header);
        return String.format("Hello! in User Service : %s", header) ;
    }

    @GetMapping("/check")
    public String check() {
        return String.format("Hi! This is a check User Service") + String.format("on PORT : %s", env.getProperty("local.server.port")) ;
    }

    @GetMapping("/health_check")
    public String status() {
        log.info(env.getProperty("welcome.message"));
        log.info(cm.getMessage());
        return this.message + String.format("on PORT : %s", env.getProperty("local.server.port")) ;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user)  {
        log.info("Controller RequestUser : {}", user);

        modelMapper.getConfiguration().setMatchingStrategy((MatchingStrategies.STRICT));
        UserDto userDto = modelMapper.map(user, UserDto.class);

        userService.createUser(userDto);

        ResponseUser responseUser = modelMapper.map(userDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ResponseUser>> getUsers() {
        Iterable<UserEntity> userList = userService.getUserByAll();

        List<ResponseUser> result = new ArrayList<>();
        userList.forEach(v -> {
            result.add(modelMapper.map(v, ResponseUser.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseUser> getUserByEmail(@PathVariable("email") String email)  {
        log.info("getUserByEmail Email : {}", email);
        
        UserDto userDto = userService.getUserByEmail(email);
        if (userDto == null) {
            return null;
        }
        else {
            ResponseUser returnUser = modelMapper.map(userDto, ResponseUser.class);

            return ResponseEntity.status(HttpStatus.OK).body(returnUser);
        }
    }

    
    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<ResponseUser> getUserByNickname(@PathVariable("nickname") String nickName)  {
        log.info("getUserByNickname Nickname : {}", nickName);
        
        UserDto userDto = userService.getUserByNickname(nickName);
        if (userDto == null) {
            return null;
        }
        else {
            ResponseUser returnUser = modelMapper.map(userDto, ResponseUser.class);
            
            return ResponseEntity.status(HttpStatus.OK).body(returnUser);
        }
    }
}
