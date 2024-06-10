package com.movie.parkplayer.repository;

import com.movie.parkplayer.entity.DetailReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRepository extends JpaRepository<DetailReviewEntity, Long> {
//    List<DetailReviewEntity> findByMovieId(Long movieId); //영화 아이디가 먼저있어야함
}
