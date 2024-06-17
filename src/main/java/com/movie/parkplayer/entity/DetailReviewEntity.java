package com.movie.parkplayer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(nullable = false)
    private Long memNum;

    @Column(nullable = false)
    private String reviewContent;

    @Column(nullable = false)
    private Integer reviewScore;

    @Column(nullable = false)
    private Date reviewDate;

    @ManyToOne
    @JoinColumn(name = "movieId", nullable = false)
    private MovieEntity movie;
}
