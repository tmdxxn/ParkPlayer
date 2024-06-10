package com.movie.parkplayer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

//    @ManyToOne
//    @JoinColumn(name = movieId, nullable = false)
//    private MovieEntity movieId;

    @Column(nullable = false)
    private String userId; // 외래 키가 아닌 일반적인 엔티티의 속성

    @Column(nullable = false)
    private String reviewContent;

    @Column(nullable = false)
    private Integer reviewScore;

    @Column(nullable = false)
    private LocalDate reviewDate;

    @Builder
    public DetailReviewEntity(
            Long reviewId, String reviewContent, Integer reviewScore, LocalDate reviewDate, String userId) { //MovieEntity movieId 영화 만들면
        this.reviewId = reviewId;
        this.reviewContent = reviewContent;
        this.reviewScore = reviewScore;
        this.reviewDate = reviewDate;
        this.userId = userId;
        //        this.movieId = movieId;
    }
}
