package com.movie.parkplayer.entity;

<<<<<<< HEAD
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


     // 기본 생성자 (빌더패턴을 사용해서 직관적으로 볼수있게 만듦)
    @Builder
    public UserEntity(Long id, String userId, String userPw, String userName, String userEmail, LocalDate userBirth, String userTell) {
        this.id = id;
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userBirth = userBirth;
        this.userTell = userTell;
=======
import com.movie.parkplayer.repository.UserRole;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

// 김승준
@Entity
@Table(name = "member")
@Getter
@ToString
@NoArgsConstructor
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memNum;

    @Column(nullable = false, unique = true)
    private String memId;

    @Column(nullable = false)
    private String memPassword;

    @Column(nullable = false, unique = true)
    private String memEmail;

    @Column(nullable = false)
    private String memTel;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date memBirth;

    @Column(nullable = false)
    private String memName;

    @Column(nullable = false)
    private Boolean memGender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Builder
    public UserEntity(String memName, Date memBirth, String memTel, String memEmail, String memPassword, String memId, Long memNum, Boolean memGender, UserRole role) {
        this.memName = memName;
        this.memBirth = memBirth;
        this.memTel = memTel;
        this.memEmail = memEmail;
        this.memPassword = memPassword;
        this.memId = memId;
        this.memNum = memNum;
        this.memGender = memGender;
        this.role = role;
    }

    public void changePassword(String newPassword) {
        this.memPassword = newPassword;
>>>>>>> 3f118e6b05b4f0920fdcd4a35fb4d0f1665c9345
    }
}
