package com.movie.parkplayer.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;
import java.util.Date;

// 회원 정보 엔티티
@Entity
@Table(name = "member")
@Getter
@ToString
@NoArgsConstructor
public class UserEntity implements Serializable {

    // 회원 정보를 DB에 저장시 구분 하기위함.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memNum;

    // 회원 ID
    @Column(nullable = false, unique = true)
    private String memId;

    // 회원 비밀번호
    @Column(nullable = false)
    private String memPassword;

    // 회원 이메일
    @Column(nullable = false)
    private String memEmail;

    // 회원 연락처(전화번호)
    @Column(nullable = false)
    private String memTel;

    // 회원 생년월일
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date memBirth;

    // 회원 이름
    @Column(nullable = false)
    private String memName;

    // 회원 성별 (True = 남성 || False = 여성)
    @Column(nullable = false)
    private Boolean memGender;

    // 회원 멤버쉽 구독 정보 (기본값 false)
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean memSubscribe = false;

    // 기본 생성자
    @Builder
    public UserEntity(String memName, Date memBirth, String memTel, String memEmail, String memPassword, String memId, Long memNum, Boolean memGender, Boolean memSubscribe) {
        this.memName = memName;
        this.memBirth = memBirth;
        this.memTel = memTel;
        this.memEmail = memEmail;
        this.memPassword = memPassword;
        this.memId = memId;
        this.memNum = memNum;
        this.memGender = memGender;
        this.memSubscribe = memSubscribe;
    }

    // 비밀번호 변경
    public void changePassword(String newPassword) {
        this.memPassword = newPassword;
    }
}