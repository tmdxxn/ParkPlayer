package com.movie.parkplayer.service;

import com.movie.parkplayer.dto.DetailReviewDTO;
import com.movie.parkplayer.entity.DetailReviewEntity;
import com.movie.parkplayer.repository.DetailReviewRepository;
import com.movie.parkplayer.entity.MovieEntity;
import com.movie.parkplayer.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DetailReviewService {

    @Autowired
    private DetailReviewRepository detailReviewRepository;

    @Autowired
    private MovieRepository movieRepository;

    public DetailReviewEntity createReview(Long movieId, DetailReviewDTO detailReviewDTO) {
        MovieEntity movieEntity = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("올바른 영화 ID가 제공되지 않았습니다."));

        detailReviewDTO.setMovie(movieEntity);

        if (detailReviewRepository.existsByMemNumAndMovie_MovieId(detailReviewDTO.getMemNum(), movieId)) {
            throw new IllegalStateException("이미 리뷰를 작성했습니다.");
        }

        detailReviewDTO.setReviewDate(new Date());

        DetailReviewEntity reviewEntity = detailReviewDTO.toEntity();

        return detailReviewRepository.save(reviewEntity);
    }

    //작성자 아이디가 맞아야함
    @Transactional
    public DetailReviewEntity updateReview(Long reviewId, DetailReviewDTO detailReviewDTO) {

        DetailReviewEntity review = detailReviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다."));

        review.setReviewContent(detailReviewDTO.getReviewContent());
        review.setReviewScore(detailReviewDTO.getReviewScore());

        return review;
    }

    //작성자 아이디가 맞아야함
    public void deleteReview(Long reviewId) {
        detailReviewRepository.deleteById(reviewId);
    }

    public Page<DetailReviewEntity> getReviewsByMovieId(Long movieId, Pageable pageable) {
        return detailReviewRepository.findByMovie_MovieId(movieId, pageable);
    }

    public double getAverageScoreByMovieId(Long movieId) {
        List<DetailReviewEntity> reviews = detailReviewRepository.findByMovie_MovieId(movieId);

        return reviews.stream().mapToInt(DetailReviewEntity::getReviewScore).average().orElse(0.0);
    }
}
