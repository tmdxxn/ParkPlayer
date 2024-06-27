package com.movie.parkplayer.entity;

import com.movie.parkplayer.repository.UserRole;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

// 김승준
@Entity
@Table(name = "member")
@Getter
@ToString
@NoArgsConstructor
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memNum")
    private Long memNum;

    @Column(name = "memId", nullable = false, unique = true)
    private String memId;

    @Column(name = "memPassword", nullable = false)
    private String memPassword;

    @Column(name = "memEmail", nullable = false, unique = true)
    private String memEmail;

    @Column(name = "memTel", nullable = false)
    private String memTel;

    @Column(name = "memBirth", nullable = false)
    private LocalDate memBirth;

    @Column(name = "memName", nullable = false)
    private String memName;

    @Column(name = "memGender" , nullable = false)
    private Boolean memGender;

    @Enumerated(EnumType.STRING)
    @Column(name = "memRole", nullable = false)
    private UserRole role;

    @Builder
    public UserEntity(String memName, LocalDate memBirth, String memTel, String memEmail, String memPassword, String memId, Long memNum, Boolean memGender, UserRole role) {
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
    }
}
