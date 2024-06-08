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

// 로그인 실패시 처리 핸들러
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        // 기본 에러 메시지 설정
        String errorMessage = "에러가 발생했습니다.";

        // 인증 예외 유형에 따라 에러 메시지 변경
        if (exception instanceof BadCredentialsException) {
            errorMessage = "아이디 혹은 비밀번호가 맞지 않습니다. 재확인 해주세요.";  // 아이디 혹은 비밀번호가 틀릴때
        } else if (exception instanceof UsernameNotFoundException) {
            errorMessage = "존재하지 않는 계정입니다. 회원가입후 로그인 해주세요.";  // 사용자 계정을 찾을 수 없음
        } else if (exception instanceof LockedException) {
            errorMessage = "계정이 잠겨있습니다. 관리자한테 문의해주세요.";  // 계정이 잠겨 있음
        }

        // 로그인 페이지로 리다이렉트하고 에러 메시지 전달
        response.sendRedirect("/login?" + errorMessage);
    }
}
