package com.movie.parkplayer.component;

import com.movie.parkplayer.service.CustomUserDetailsService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = authentication.getName();
        String pw = authentication.getCredentials().toString();
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(id);

        if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("계정이 잠겨있습니다. 관리자에게 문의해주세요.");
        }
        if (!userDetails.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("비밀번호가 만료되었습니다. 비밀번호를 변경해주세요.");
        }
        if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException("계정이 만료되었습니다. 관리자에게 문의해주세요.");
        }
        if (!userDetails.isEnabled()) {
            throw new DisabledException("계정이 비활성화되었습니다. 관리자에게 문의해주세요.");
        }

        if (passwordEncoder.matches(pw, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(userDetails, pw, userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("아이디 혹은 비밀번호가 맞지 않습니다. 재확인 해주세요.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
