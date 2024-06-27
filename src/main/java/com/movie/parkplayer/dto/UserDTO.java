package com.movie.parkplayer.dto;

import com.movie.parkplayer.entity.UserEntity;
import com.movie.parkplayer.repository.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// 김승준
@Data
@NoArgsConstructor
public class UserDTO {


    private Long memNum;

    private String memId;

    private String memPassword;

    private String memEmail;

    private String memTel;

    private LocalDate memBirth;

    private String memName;

    private Boolean memGender;

    private UserRole role;

    public UserEntity toEntity(String encodedPassword) {
        return UserEntity.builder()
                .memName(memName)
                .memBirth(memBirth)
                .memTel(memTel)
                .memEmail(memEmail)
                .memPassword(encodedPassword)
                .memId(memId)
                .memNum(memNum)
                .memGender(memGender)
                .role(role)
                .build();
    }
}
