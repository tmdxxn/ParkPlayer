package com.movie.parkplayer.repository;

import com.movie.parkplayer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // 사용자 아이디를 기준으로 찾음 (아이디는 중복 불가하기 때문)
    UserEntity findByUserId(String userId);
=======
import java.util.Optional;

// 김승준
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByMemId(String memId);
>>>>>>> 3f118e6b05b4f0920fdcd4a35fb4d0f1665c9345
}
