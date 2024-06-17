package com.movie.parkplayer.dto;

import com.movie.parkplayer.entity.DetailReviewEntity;
import com.movie.parkplayer.entity.MovieEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailReviewDTO {
    private Long reviewId;

    private String reviewContent;

    private Integer reviewScore;

    private Date reviewDate;

    private Long memNum;

    private MovieEntity movie;

    public DetailReviewEntity toEntity() {
        return DetailReviewEntity.builder()
                .reviewId(reviewId)
                .reviewContent(reviewContent)
                .reviewScore(reviewScore)
                .reviewDate(reviewDate)
                .memNum(memNum)
                .movie(movie)
                .build();
    }
}
