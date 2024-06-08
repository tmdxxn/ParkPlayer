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
    private Long noti_id;

    @Column(nullable = false)
    private String mem_id;

    @Column(nullable = false)
    private String noti_title;

    @Column(nullable = false)
    private String noti_category;

    @Column(nullable = false)
    private String noti_content;

    @Column(nullable = false)
    private Date noti_date;
    
    //생성자
    @Builder
    public Notice(Long noti_id, String mem_id, String noti_title, String noti_content, Date noti_date, String noti_category) {
        this.noti_id = noti_id;
        this.mem_id = mem_id;
        this.noti_title = noti_title;
        this.noti_content = noti_content;
        this.noti_date = noti_date;
        this.noti_category = noti_category;
    }
}
