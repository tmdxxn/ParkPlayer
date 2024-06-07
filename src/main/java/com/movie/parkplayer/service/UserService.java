package com.movie.parkplayer.service;

import com.movie.parkplayer.entity.UserEntity;
import com.movie.parkplayer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 회원 서비스 로직
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 회원가입
    public void saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }


    public UserEntity findByUsername(String username) {
        return userRepository.findByMemId(username)
                .orElseThrow(() -> new UsernameNotFoundException("회원을 찾을수 없습니다 : " + username));
    }
}
