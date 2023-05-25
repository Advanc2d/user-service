package com.example.user.phm.repository;

import com.example.user.phm.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByNickname(String nickname);
    UserEntity findByEmail(String email);
}
