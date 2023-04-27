package com.example.user.phm.service;

import com.example.user.phm.dto.UserDto;
import com.example.user.phm.entity.UserEntity;

public interface UserService {
    boolean findByEmail(String email) throws Exception;

    UserDto createUser(UserDto user);
}
