package com.movie.parkplayer.component;

import com.movie.parkplayer.service.CustomUserDetailsService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// 사용자 정의 인증 로직을 구현
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(@Lazy CustomUserDetailsService customUserDetailsService, @Lazy PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // 사용자가 입력한 이름과 비밀번호를 가져옴
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // 사용자 정보를 로드함
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        // 사용자 계정의 상태를 확인
        if (!userDetails.isAccountNonLocked() || !userDetails.isCredentialsNonExpired() || !userDetails.isAccountNonExpired() || !userDetails.isEnabled()) {
            throw new BadCredentialsException("계정이 올바르지 않습니다.");
        }

        // 비밀번호가 일치하는지 확인
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            // 인증이 성공하면 인증된 사용자 정보를 포함한 Authentication 객체를 반환
            return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        } else {
            // 비밀번호가 일치하지 않으면 예외를 발생
            throw new BadCredentialsException("아이디 혹은 비밀번호가 맞지 않습니다. 재확인 해주세요.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
