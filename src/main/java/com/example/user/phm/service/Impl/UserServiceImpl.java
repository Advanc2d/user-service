package com.example.user.phm.service.Impl;

import com.example.user.phm.dto.UserDto;
import com.example.user.phm.entity.UserEntity;
import com.example.user.phm.repository.UserRepository;
import com.example.user.phm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.MemberSubstitution;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

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
        Optional<UserEntity> optional = userRepository.findByEmail(email);

        if (!optional.isPresent()) {
            return false;
        }

        UserEntity entity = optional.get();
        UserDto userDto = modelMapper.map(entity, UserDto.class);
        log.info("user email : {}", userDto.getEmail());
        return true;
    }

    @Transactional
    @Override
    public UserDto createUser(UserDto user) {
        log.info("create user {}", user);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity entity = modelMapper.map(user, UserEntity.class);

        entity.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(entity);

        UserDto userDto = modelMapper.map(entity, UserDto.class);
        return userDto;
    }

//    private UserEntity getMyUserByEmail(String email) {
//        return userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }

//    public UserDto getUserDetailByEmail(String email) {
//        UserEntity entity = getMyUserByEmail(email);
//        return toObject(entity, UserDto.class);
//    }
}
