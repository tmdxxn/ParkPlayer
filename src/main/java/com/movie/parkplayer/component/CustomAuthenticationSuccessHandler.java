package com.movie.parkplayer.component;

import com.movie.parkplayer.util.JWTTokenUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

// 김승준
@Component
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JWTTokenUtil jwtTokenUtil;

    public CustomAuthenticationSuccessHandler(JWTTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info(authentication.getName() + "님이 성공적으로 로그인 하였습니다.");

        String token = jwtTokenUtil.generateToken(authentication.getName());
        log.info("생성된 JWT 토큰 : " + token);

        // JWT 토큰을 응답 헤더에 추가
        response.setHeader("Authorization", "Bearer " + token);
        log.info("응답 헤더에 JWT토큰이 추가되었습니다.");

        // SecurityContext 설정
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("인증으로 보안 컨텍스트 업데이트되었습니다.");

        response.sendRedirect("/");
        log.info("Redirecting to main");
    }
}
