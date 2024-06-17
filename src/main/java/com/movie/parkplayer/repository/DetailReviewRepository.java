package com.movie.parkplayer.repository;

import com.movie.parkplayer.entity.DetailReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailReviewRepository extends JpaRepository<DetailReviewEntity, Long> {
    Page<DetailReviewEntity> findByMovie_MovieId(Long movieId, Pageable pageable);
    List<DetailReviewEntity> findByMovie_MovieId(Long movieId);
    boolean existsByMemNumAndMovie_MovieId(Long memNum, Long movieId);
}
