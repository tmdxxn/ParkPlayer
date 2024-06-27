package com.movie.parkplayer.controller;

import com.movie.parkplayer.dto.UserDTO;
import com.movie.parkplayer.entity.UserEntity;
import com.movie.parkplayer.repository.UserRole;
import com.movie.parkplayer.service.CustomUserDetailsService;
import com.movie.parkplayer.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// 김승준
@Controller
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;

    public UserController(PasswordEncoder passwordEncoder, UserService userService, CustomUserDetailsService customUserDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "register";
    }

    @PostMapping("/signup")
    public String Signup(UserDTO userDTO, RedirectAttributes redirectAttributes) {
        try {
            String encodedPassword = passwordEncoder.encode(userDTO.getMemPassword());
            userDTO.setMemPassword(encodedPassword);
            userDTO.setRole(UserRole.USER);

            UserEntity userEntity = userDTO.toEntity(encodedPassword);
            userService.saveUser(userEntity);

            redirectAttributes.addFlashAttribute("message", "회원가입이 완료되었습니다.");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "회원가입 중 오류가 발생했습니다. 다시 시도해주세요.");
            return "redirect:/signup";
        }
    }
}
