package com.escass.movieproject.services.user;

import com.escass.movieproject.DTO.MyReviewDTO;
import com.escass.movieproject.DTO.ReviewDTO;
import com.escass.movieproject.entities.user.ReviewEntity;
import com.escass.movieproject.mappers.user.ReviewMapper;
import com.escass.movieproject.results.user.ReviewResult;
import com.escass.movieproject.vos.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ReviewService {
    private final ReviewMapper reviewMapper;

    public ReviewService(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    public ReviewResult postReview(ReviewEntity review) {
        if(review == null || review.getReContent() == null) {
            return ReviewResult.FAILURE;
        }
        review.setReCreatedAt(LocalDateTime.now());
        review.setReUpdatedAt(null);
        review.setReDeletedAt(null);
        review.setReLiked(0);
        return this.reviewMapper.insertReview(review) > 0 ? ReviewResult.SUCCESS : ReviewResult.FAILURE;
    }

    public Pair<PageVo, ReviewDTO[]> getReviewById(int id, int page) {
        page = Math.max(1, page);
        int totalCount = this.reviewMapper.selectArticleCountByMovieId(id);
        PageVo pageVo = new PageVo(page, totalCount);

        ReviewDTO[] reviewDTO = this.reviewMapper.selectArticleByMovieId(
                id,
                pageVo.countPerPage,
                pageVo.offsetCount);
        return Pair.of(pageVo, reviewDTO);
    }

    public boolean incrementLike(int reviewId) {
        return this.reviewMapper.incrementLikeCount(reviewId) > 0;
    }

    public boolean hasUserWrittenReview(int movieId, int userId) {
        return this.reviewMapper.countUserReviewsForMovie(movieId, userId) > 0;
    }

    public ReviewEntity getReviewsById (int reviewId) {
        return this.reviewMapper.getReviewsById(reviewId);
    }

    public boolean updateReview(ReviewEntity updatedReview) {
        int rowsAffected = this.reviewMapper.updateReview(updatedReview);
        return rowsAffected > 0; // 수정 성공 여부 반환
    }

    public boolean deleteReview(int reviewId) {
        int rowsAffected = this.reviewMapper.deleteReview(reviewId);
        return rowsAffected > 0;
    }

    public Pair<PageVo, List<MyReviewDTO>> getReviewByUser(int page, int userId) {
        page = Math.max(1, page);
        int totalCount = this.reviewMapper.selectArticleCountByUserId(userId);
        PageVo pageVo = new PageVo(page, totalCount);

        List<MyReviewDTO> reviewDTO = this.reviewMapper.selectArticleByUserId(userId, pageVo.countPerPage, pageVo.offsetCount);
        return Pair.of(pageVo, reviewDTO);
    }


}
