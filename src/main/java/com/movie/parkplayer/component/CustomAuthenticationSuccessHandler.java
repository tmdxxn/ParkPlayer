package com.movie.parkplayer.component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Logger;

// 사용자가 로그인에 성공했을 때 처리
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    // 이벤트, 오류를 기록하기 위한 로그 객체 사용
    private static final Logger logger = Logger.getLogger(CustomAuthenticationSuccessHandler.class.getName());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 로그인 성공 로그 기록
        logger.info("User " + authentication.getName() + "님이 성공적으로 로그인 하였습니다.");

        // 로그인 성공 -> 메인페이지 리다이렉트
        response.sendRedirect("/main");

        // 추가 로직이 필요한 경우 여기에 추가
        // 예: 세션에 사용자 정보 저장, 이벤트 발생 등
    }
}
