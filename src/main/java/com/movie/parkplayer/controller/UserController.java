package com.movie.parkplayer.controller;

import com.movie.parkplayer.entity.UserEntity;
import com.movie.parkplayer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    // 메인화면 보내줄 매핑
    @GetMapping("/")
    public String home() {
        return "메인화면 만들어지면 그거 넣으면 됨.";
    }

    // 회원가입창 보내줄 매핑
    @GetMapping("/member/signup")
    public String create() {
        return "회원가입창";
    }

    // 회원가입 create()
    @PostMapping("/")
    public String create(@RequestParam String memId,
                         @RequestParam String memPassword,
                         @RequestParam String memEmail,
                         @RequestParam String memTel,
                         @RequestParam Boolean memGender,
                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date memBirth,
                         @RequestParam String memName) {

        UserEntity userEntity = UserEntity.builder()
                .memId(memId)
                .memPassword(memPassword)
                .memEmail(memEmail)
                .memTel(memTel)
                .memGender(memGender)
                .memBirth(memBirth)
                .memName(memName)
                .build();

        userService.save(userEntity);
        return "로그인창으로 가야함";
    }

    // 로그인 login()
    @GetMapping("/")
    public String login() {
        return "";
    }


}
