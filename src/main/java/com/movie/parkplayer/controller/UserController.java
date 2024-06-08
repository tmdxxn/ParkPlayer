package com.movie.parkplayer.controller;

import com.movie.parkplayer.dto.UserDTO;
import com.movie.parkplayer.entity.UserEntity;
import com.movie.parkplayer.service.CustomUserDetailsService;
import com.movie.parkplayer.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

// 회원 관련 로직
@Controller
@RequestMapping("/member")
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;

    public UserController(PasswordEncoder passwordEncoder, UserService userService, CustomUserDetailsService customUserDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.customUserDetailsService = customUserDetailsService;
    }

    // 로그인 폼
    @GetMapping("/login")
    public String LoginForm() {
        return "login";
    }

    // (임시) 로그인 완료시
    @GetMapping("/welcome")
    public String welcome(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "welcome";
    }

    // 회원가입 폼
    @GetMapping("/signup")
    public String SignupForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "register";
    }

    // 회원가입
    @PostMapping("/signup")
    public String processSignup(UserDTO userDTO, @RequestParam(value = "subscribe", required = false, defaultValue = "false") Boolean subscribe, RedirectAttributes redirectAttributes) {
        try {

            // 비밀번호 암호화 작업
            String encodedPassword = passwordEncoder.encode(userDTO.getMemPassword());
            userDTO.setMemPassword(encodedPassword);

            // 회원 구독정보 false 설정
            userDTO.setMemSubscribe(subscribe);

            // UserDTO ->UserEntity
            UserEntity userEntity = userDTO.toEntity(encodedPassword);

            // 저장
            userService.saveUser(userEntity);

            // 성공
            redirectAttributes.addFlashAttribute("message", "회원가입이 완료되었습니다.");
            return "redirect:/member/login";
        } catch (Exception e) {
            // 실패
            redirectAttributes.addFlashAttribute("errorMessage", "회원가입중 오류가 발생했습니다. 다시 시도해주세요.");
            return "redirect:/member/signup";
        }
    }


}
