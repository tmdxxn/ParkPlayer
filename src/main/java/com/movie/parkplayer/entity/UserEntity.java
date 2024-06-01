package com.movie.parkplayer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "member")
@Getter // 게터 사용해서 모든 필드 접근 가능하게끔.. 세터는 보안위험성때문에 제외
@ToString
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String userPw;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private LocalDate userBirth;

    @Column(nullable = false)
    private String userTell;


    @Builder
    public UserEntity(Long id, String userId, String userPw, String userName, String userEmail, LocalDate userBirth, String userTell) {
        this.id = id;
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userBirth = userBirth;
        this.userTell = userTell;
    }
}
