package com.movie.parkplayer.dto;

import com.movie.parkplayer.entity.UserEntity;
<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String userId;

    private String userPw;

    private String userName;

    private String userEmail;

    private LocalDate userBirth;

    private String userTell;

    // DTO -> Entity 변환
    public UserEntity toEntity() {
        return UserEntity.builder()
                .id(id)
                .userId(userId)
                .userName(userName)
                .userEmail(userEmail)
                .userBirth(userBirth)
                .userTell(userTell)
                .build();
    }


=======
import com.movie.parkplayer.repository.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

// 김승준
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
>>>>>>> 3f118e6b05b4f0920fdcd4a35fb4d0f1665c9345
}
