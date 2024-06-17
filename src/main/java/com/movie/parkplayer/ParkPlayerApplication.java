package com.movie.parkplayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)   // 스프링 시큐리티 로그인창 없애기.
public class ParkPlayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkPlayerApplication.class, args);
    }

}
