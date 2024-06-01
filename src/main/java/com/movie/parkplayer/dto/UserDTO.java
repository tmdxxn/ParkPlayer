package com.movie.parkplayer.dto;

import com.movie.parkplayer.entity.UserEntity;
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


}
