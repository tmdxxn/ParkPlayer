package com.movie.parkplayer.service;

import com.movie.parkplayer.component.CustomUserDetails;
import com.movie.parkplayer.entity.UserEntity;
import com.movie.parkplayer.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 인증시 사용자 정보
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 사용자 이름으로 사용자 엔티티를 조회
        UserEntity userEntity = userRepository.findByMemId(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 찾을수 없습니다."));

        // 조회된 사용자 엔티티를 UserDetails 객체로 변환하여 반환
        return new CustomUserDetails(userEntity);
    }
}
