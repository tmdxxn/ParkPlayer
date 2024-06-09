package com.movie.parkplayer.component;

import com.movie.parkplayer.service.CustomUserDetailsService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

// 사용자 정의 인증 로직을 구현
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    // 이벤트, 오류를 기록하기 위한 로그 객체 사용
    private static final Logger logger = Logger.getLogger(CustomAuthenticationProvider.class.getName());

    private final CustomUserDetailsService customUserDetailsService;

    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // 사용자가 입력한 이름과 비밀번호를 가져옴
        String id = authentication.getName();
        String pw = authentication.getCredentials().toString();

        // 사용자 정보를 로드함
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(id);

        // 사용자 계정의 상태를 확인
        if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("계정이 잠겨있습니다. 관리자한테 문의해주세요.");
        }
        if (!userDetails.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("비밀번호가 만료되었습니다. 비밀번호를 변경해주세요.");
        }
        if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException("계정이 만료되었습니다. 관리자한테 문의해주세요.");
        }
        if (!userDetails.isEnabled()) {
            throw new DisabledException("계정이 비활성화되었습니다. 관리자한테 문의해주세요.");
        }

        // 비밀번호가 일치하는지 확인
        if (passwordEncoder.matches(pw, userDetails.getPassword())) {
            // 인증이 성공하면 인증된 사용자 정보를 포함한 Authentication 객체를 반환
            return new UsernamePasswordAuthenticationToken(userDetails, pw, userDetails.getAuthorities());
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
