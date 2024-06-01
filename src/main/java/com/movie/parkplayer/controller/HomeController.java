package com.movie.parkplayer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "메인화면 만들어지면 그거 넣으면 됨.";
    }
}
