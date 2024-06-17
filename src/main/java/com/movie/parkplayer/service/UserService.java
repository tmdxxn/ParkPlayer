package com.movie.parkplayer.service;

import com.movie.parkplayer.entity.UserEntity;
import com.movie.parkplayer.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    public UserEntity findByMemId(String memId) {
        return userRepository.findByMemId(memId)
                .orElseThrow(() -> new UsernameNotFoundException("회원을 찾을 수 없습니다: " + memId));
    }
}
