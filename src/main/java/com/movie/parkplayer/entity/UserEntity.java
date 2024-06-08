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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memNum;

    @Column(nullable = false, unique = true)
    private String memId;

    @Column(nullable = false)
    private String memPassword;

    @Column(nullable = false)
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

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean memSubscribe = false;


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

    public void changePassword(String newPassword) {
        this.memPassword = newPassword;
    }
}