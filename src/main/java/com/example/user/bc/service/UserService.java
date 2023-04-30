package com.example.user.bc.service;

import com.example.user.bc.domain.UserEntity;
import com.example.user.bc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> findUserByEmail(String email) throws Exception {
        return userRepository.findUserByEmail(email);
    }

    public Optional<UserEntity> findUserByUserId(Long userId) throws Exception{
        return userRepository.findUserByUserId(userId);
    }
}
