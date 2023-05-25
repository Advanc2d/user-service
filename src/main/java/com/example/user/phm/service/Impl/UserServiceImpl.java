package com.example.user.phm.service.Impl;

import com.example.user.phm.dto.UserDto;
import com.example.user.phm.entity.UserEntity;
import com.example.user.phm.repository.UserRepository;
import com.example.user.phm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import javax.transaction.Transactional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository, ModelMapper modelMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    // 사용자 이메일 조회(Email)
    public boolean findByEmail(String email) throws Exception {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) {
            return false;
        }

        UserDto userDto = modelMapper.map(userEntity, UserDto.class);
        log.info("user email : {}", userDto.getEmail());
        return true;
    }

    @Transactional
    @Override
    public UserDto createUser(UserDto user) {
        log.info("ServiceImpl createUser {}", user);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity entity = modelMapper.map(user, UserEntity.class);
        entity.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(entity);

        UserDto userDto = modelMapper.map(entity, UserDto.class);
        return userDto;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) {
            throw new UsernameNotFoundException("User no found");
        }

        UserDto userDto = modelMapper.map(userEntity, UserDto.class);

        return userDto;
    }

    @Override
    public Iterable<UserEntity> getUserByAll() {
        // UserEntity userEntity = userRepository.findByEmail(email);
        return userRepository.findAll();
    }
//    private UserEntity getMyUserByEmail(String email) {
//        return userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }

        return new User(userEntity.getEmail(), userEntity.getPassword(),
                true, true, true, true,
                new ArrayList<>());
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }

        UserDto userDto = modelMapper.map(userEntity, UserDto.class);
        return userDto;
    }
//    public UserDto getUserDetailByEmail(String email) {
//        UserEntity entity = getMyUserByEmail(email);
//        return toObject(entity, UserDto.class);
//    }
}