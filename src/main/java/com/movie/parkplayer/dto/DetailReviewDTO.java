package com.movie.parkplayer.dto;

import com.movie.parkplayer.entity.DetailReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DetailReviewDTO {
    private Long reviewId;

    private String reviewContent;

    private Integer reviewScore;

    private LocalDate reviewDate;

    private String userId;

//    private movieEntity movieId;

    public DetailReviewEntity toEntity() {
        return DetailReviewEntity.builder()
                .reviewId(reviewId)
                .reviewContent(reviewContent)
                .reviewScore(reviewScore)
                .reviewDate(reviewDate)
                .userId(userId)
//                .movieId(movieId)
                .build();
    }
}
