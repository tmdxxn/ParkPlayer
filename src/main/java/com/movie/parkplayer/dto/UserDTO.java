package com.movie.parkplayer.dto;

import com.movie.parkplayer.entity.UserEntity;
import com.movie.parkplayer.repository.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long memNum;
    private String memId;
    private String memPassword;
    private String memEmail;
    private String memTel;
    private Date memBirth;
    private String memName;
    private Boolean memGender;
    private Boolean memSubscribe = false;
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
                .memSubscribe(memSubscribe)
                .role(role)
                .build();
    }
}
