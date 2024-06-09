package com.movie.parkplayer.component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Logger;

// 로그인 실패시 처리 핸들러
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    // 이벤트, 오류를 기록하기 위한 로그 객체 사용
    private static final Logger logger = Logger.getLogger(CustomAuthenticationFailureHandler.class.getName());

    // 로그인 실패시 할 동작 인테 페이스 구현 (클라이언트 요청정보, 서버 응답정보, 발생한 예외)
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        // 기본 에러 메시지 설정
        String errorMessage = "에러가 발생했습니다.";

        // 인증 예외 유형에 따라 에러 메시지 변경
        if (exception instanceof BadCredentialsException) {
            errorMessage = "아이디 혹은 비밀번호가 맞지 않습니다. 재확인 해주세요.";
        } else if (exception instanceof UsernameNotFoundException) {
            errorMessage = "존재하지 않는 계정입니다. 회원가입후 로그인 해주세요.";
        } else if (exception instanceof LockedException) {
            errorMessage = "계정이 잠겨있습니다. 관리자한테 문의해주세요.";
        }

        // 예외 메시지를 로그로 기록
        logger.warning("Authentication failed: " + exception.getMessage());

        // 세션에 에러 메시지 저장
        request.getSession().setAttribute("error", errorMessage);

        // 로그인 페이지로 리다이렉트
        response.sendRedirect("/login");
    }
}
