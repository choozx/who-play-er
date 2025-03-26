package com.yjh.whoplayer.repository;

import com.yjh.whoplayer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByErNickname(String nickname);
}
