package com.movie.parkplayer.repository;

import com.movie.parkplayer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserId(String userId);   // 사용자 아이디를 기준으로 찾음
}
