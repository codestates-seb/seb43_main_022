package com.codea.review;

import com.codea.exception.BusinessLogicException;
import com.codea.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.codea.review.Review.ReviewStatus.REVIEW_VALID;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review updateReview(long id, Review review) {
        Review findReview = findReview(id);

        Optional.ofNullable(review.getTitle()).ifPresent(title -> findReview.setTitle(title));
        Optional.ofNullable(review.getContent()).ifPresent(content -> findReview.setContent(content));
        Optional.ofNullable(review.getPhoto()).ifPresent(photo -> findReview.setPhoto(photo));
        Optional.ofNullable(review.getRating()).ifPresent(rating -> findReview.setRating(rating));
        findReview.setModified_at(LocalDateTime.now());

        return reviewRepository.save(findReview);
    }

    public Review findReview(long id) {
        return reviewRepository.findById(id).orElseThrow(() -> new BusinessLogicException(ExceptionCode.REVIEW_NOT_FOUND));
    }

    public Page<Review> findReviews(int page, int size) {
        return reviewRepository.findByStatus(REVIEW_VALID, PageRequest.of(page, size, Sort.by("id").descending()));
    }

    public void deleteReview(long id) {
        Review findReview = findReview(id);

        findReview.setStatus(Review.ReviewStatus.REVIEW_DELETED);
        reviewRepository.save(findReview);
    }
}
