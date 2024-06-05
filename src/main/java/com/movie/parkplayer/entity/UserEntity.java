package com.movie.parkplayer.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "member")
@Getter // 게터 사용해서 모든 필드 접근 가능하게끔.. 세터는 보안위험성때문에 제외
@ToString
@NoArgsConstructor
public class UserEntity {

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


     // 기본 생성자 (빌더패턴을 사용해서 직관적으로 볼수있게 만듦)
    @Builder
    public UserEntity(String memName, Date memBirth, String memTel, String memEmail, String memPassword, String memId, Long memNum, Boolean memGender) {
        this.memName = memName;
        this.memBirth = memBirth;
        this.memTel = memTel;
        this.memEmail = memEmail;
        this.memPassword = memPassword;
        this.memId = memId;
        this.memNum = memNum;
        this.memGender = false;
    }
}
