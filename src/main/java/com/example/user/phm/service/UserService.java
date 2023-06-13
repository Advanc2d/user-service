package com.example.user.phm.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.user.phm.dto.UserDto;
import com.example.user.phm.entity.UserEntity;

public interface UserService extends UserDetailsService {
    boolean findByEmail(String email) throws Exception;

    UserDto createUser(UserDto user);
    UserDto getUserByEmail(String email);
    UserDto getUserByNickname(String nickName);

    Iterable<UserEntity> getUserByAll();

    UserDto getUserDetailsByEmail(String userName);
}