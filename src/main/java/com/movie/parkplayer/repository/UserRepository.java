package com.movie.parkplayer.repository;

import com.movie.parkplayer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 김승준
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByMemId(String memId);
    Optional<UserEntity> findByMemEmail(String memEmail);
}
