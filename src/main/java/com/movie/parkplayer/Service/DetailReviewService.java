package com.movie.parkplayer.Service;

import com.movie.parkplayer.entity.DetailReviewEntity;
import com.movie.parkplayer.repository.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class DetailReviewService {
    @Autowired
    private DetailRepository detailRepository;

    //write modify delete page score score.avg
    //write
    public void write(DetailReviewEntity detailReviewEntity) {
        detailRepository.save(detailReviewEntity);
    }

    //list
    public Page<DetailReviewEntity> getReviews(int page, int size) {
        return detailRepository.findAll(PageRequest.of(page, size));
    }

    //update
    public DetailReviewEntity update(Long reviewId, DetailReviewEntity updateReview) {
        DetailReviewEntity existingReview = detailRepository.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("Invalid review ID"));
        existingReview.setReviewContent(updateReview.getReviewContent());
        existingReview.setReviewScore(updateReview.getReviewScore());
        existingReview.setReviewDate(updateReview.getReviewDate());
        return detailRepository.save(existingReview);
    }

    //delete
    public void delete(Long reviewId) {
        detailRepository.deleteById(reviewId);
    }

}
