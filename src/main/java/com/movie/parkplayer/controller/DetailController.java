package com.movie.parkplayer.controller;

import com.movie.parkplayer.Service.DetailReviewService;
import com.movie.parkplayer.entity.DetailReviewEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DetailController {
    @Autowired
    private DetailReviewService detailReviewService;

    //리스트
    @GetMapping("/reviewList")
    public String reviewList(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<DetailReviewEntity> reviews = detailReviewService.getReviews(page, 5);
        model.addAttribute("reviews", reviews.getContent());
        model.addAttribute("totalPages", reviews.getTotalPages());
        model.addAttribute("currentPage", page);
        return "detail";
    }

    //작성
    @PostMapping("/review")
    public String reviewWrite(DetailReviewEntity detailReviewEntity) {
        detailReviewService.write(detailReviewEntity);

        return "redirect:/";
    }

    //수정
    @PutMapping("/review/{reviewId}")
    public DetailReviewEntity reviewUpdate(@PathVariable("reviewId") Long reviewId, @RequestBody DetailReviewEntity updateReview) {

        return detailReviewService.update(reviewId, updateReview);
    }

    //삭제
    @DeleteMapping("/review/{reviewId}")
    @ResponseBody
    public void reviewDelete(@PathVariable("reviewId") Long reviewId) {

        detailReviewService.delete(reviewId);
    }
}
