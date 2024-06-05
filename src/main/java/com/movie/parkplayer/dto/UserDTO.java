package com.movie.parkplayer.dto;

import com.movie.parkplayer.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long memNum;

    private String memId;

    private String memPassword;

    private String memEmail;

    private String memTel;

    private Date memBirth;

    private String memName;

    private Boolean memGender;


    // DTO -> Entity 변환
    public UserEntity toEntity() {
        return UserEntity.builder()
                .memNum(memNum)
                .memId(memId)
                .memPassword(memPassword)
                .memEmail(memEmail)
                .memTel(memTel)
                .memBirth(memBirth)
                .memName(memName)
                .memGender(memGender)
                .build();
    }
}
