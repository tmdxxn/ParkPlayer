package com.movie.parkplayer.controller;

import com.movie.parkplayer.dto.DetailReviewDTO;
import com.movie.parkplayer.entity.DetailReviewEntity;
import com.movie.parkplayer.repository.DetailReviewRepository;
import com.movie.parkplayer.entity.MovieEntity;
import com.movie.parkplayer.repository.MovieRepository;
import com.movie.parkplayer.service.DetailReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//관리자 삭제 기능 추가해야함

@Controller
@RequestMapping("/detail/{movieId}")
public class DetailController {

    @Autowired
    private DetailReviewService detailReviewService;
    @Autowired
    private DetailReviewRepository detailReviewRepository;
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("")
    public String showMovieDetail(@PathVariable Long movieId, Pageable pageable,Model model) {
        // 영화 상세 정보 로직 추가 (영화 정보)

        model.addAttribute("movieId", movieId);
        model.addAttribute("reviews", detailReviewService.getReviewsByMovieId(movieId, pageable));
        model.addAttribute("averageScore", detailReviewService.getAverageScoreByMovieId(movieId));

        return "detail";
    }

    //리뷰 작성
    @PostMapping("/review")
    public ResponseEntity<?> createReview(@PathVariable Long movieId, @RequestBody DetailReviewDTO detailReviewDTO) {
        try {
            if (detailReviewDTO.getMovie() == null || detailReviewDTO.getMovie().getMovieId() == null) {
                throw new IllegalArgumentException("올바른 영화 정보가 제공되지 않았습니다.");
            }

            detailReviewDTO.getMovie().setMovieId(movieId);

            //리뷰 중복검사
            if (detailReviewRepository.existsByMemNumAndMovie_MovieId(detailReviewDTO.getMemNum(), detailReviewDTO.getMovie().getMovieId())) {
                throw new IllegalStateException("이미 리뷰를 작성했습니다.");
            }

            detailReviewDTO.setReviewDate(new Date());

            DetailReviewEntity createdReview = detailReviewRepository.save(detailReviewDTO.toEntity());

            return ResponseEntity.ok(createdReview);
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //리뷰 수정
    @PutMapping("/review/{reviewId}")
    public ResponseEntity<DetailReviewEntity> updateReview(@PathVariable Long movieId, @PathVariable Long reviewId, @RequestBody DetailReviewDTO detailReviewDTO) {
        detailReviewDTO.setMovie(new MovieEntity(movieId));
        DetailReviewEntity updatedReview = detailReviewService.updateReview(reviewId, detailReviewDTO);

        return ResponseEntity.ok(updatedReview);
    }

    //리뷰 삭제 + 관리자 삭제기능 추가 예정
    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long movieId, @PathVariable Long reviewId) {
        detailReviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }

    //리뷰 불러오기 (페이지 10개씩 보기, 사이즈 5개)
    @GetMapping("/reviews")
    public ResponseEntity<Map<String, Object>> getReviews(@PathVariable Long movieId,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DetailReviewEntity> reviewPage = detailReviewService.getReviewsByMovieId(movieId, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("reviews", reviewPage.getContent());
        response.put("currentPage", reviewPage.getNumber());
        response.put("totalItems", reviewPage.getTotalElements());
        response.put("totalPages", reviewPage.getTotalPages());
        response.put("averageScore", detailReviewService.getAverageScoreByMovieId(movieId));

        return ResponseEntity.ok(response);
    }
}
