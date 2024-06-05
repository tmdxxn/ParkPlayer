package com.movie.parkplayer.repository;

import com.movie.parkplayer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // 사용자 아이디를 기준으로 찾음 (아이디는 중복 불가하기 때문)
    UserEntity findByMemId(String memId);
}
