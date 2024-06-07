package com.movie.parkplayer.config;

import com.movie.parkplayer.component.CustomAuthenticationProvider;
import com.movie.parkplayer.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

// 보안 설정
@Configuration
@EnableWebSecurity
public class SecurityConfig extends AbstractSecurityWebApplicationInitializer {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 인증 관리를 설정하는 AuthenticationManager 빈을 생성합니다.
     *
     * @param http HttpSecurity 객체
     * @param customAuthenticationProvider 사용자 정의 인증 프로바이더
     * @return AuthenticationManager 객체
     * @throws Exception 예외 발생 시
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, CustomAuthenticationProvider customAuthenticationProvider) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .authenticationProvider(customAuthenticationProvider);
        return auth.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        // 접근 권한 설정
                        .requestMatchers("/", "/member/signup", "/member/login").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        // 로그인 설정
                        .loginPage("/member/login") // 사용자 정의 로그인 페이지
                        .loginProcessingUrl("/login") // 로그인 처리 URL
                        .defaultSuccessUrl("/member/welcome", true) // 로그인 성공 후 이동할 페이지
                        .permitAll()
                )
                .logout((logout) -> logout
                        // 로그아웃 설정
                        .logoutUrl("/logout") // 로그아웃 처리 URL
                        .logoutSuccessUrl("/") // 로그아웃 성공 후 이동할 페이지
                        .permitAll()
                );

        return http.build();
    }
}
