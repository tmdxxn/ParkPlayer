package com.movie.parkplayer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter // 게터 사용해서 모든 필드 접근 가능하게끔.. 세터는 보안위험성때문에 제외
@ToString
@NoArgsConstructor
@Entity
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notiId;

    @Column(nullable = false)
    private String memId;

    @Column(nullable = false)
    private String notiTitle;

    @Column(nullable = false)
    private String notiCategory;

    @Column(nullable = false)
    private String notiContent;

    @Column(nullable = false)
    private Date notiDate;
    
    //생성자
    @Builder
    public Notice(Long notiId, String memId, String notiTitle, String notiContent, Date notiDate, String notiCategory) {
        this.notiId = notiId;
        this.memId = memId;
        this.notiTitle = notiTitle;
        this.notiContent = notiContent;
        this.notiDate = notiDate;
        this.notiCategory = notiCategory;
    }
}
