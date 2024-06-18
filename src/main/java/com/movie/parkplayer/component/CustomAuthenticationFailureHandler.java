package com.movie.parkplayer.component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

// 김승준
@Component
@Slf4j
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = "에러가 발생했습니다.";

        if (exception instanceof BadCredentialsException) {
            errorMessage = "아이디 혹은 비밀번호가 맞지 않습니다. 재확인 해주세요.";
        } else if (exception instanceof UsernameNotFoundException) {
            errorMessage = "존재하지 않는 계정입니다. 회원가입 후 로그인 해주세요.";
        } else if (exception instanceof LockedException) {
            errorMessage = "계정이 잠겨있습니다. 관리자에게 문의해주세요.";
        }

        log.warn("인증 실패: " + exception.getMessage());

        request.getSession().setAttribute("에러 : ", errorMessage);
        response.sendRedirect("/login");
    }
}
