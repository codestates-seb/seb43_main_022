package com.codea.review;

import com.codea.exception.ExceptionCode;
import com.codea.exception.BusinessLogicException;
import com.codea.restaurant.Restaurant;
import com.codea.restaurant.RestaurantRepository;
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
    private final RestaurantRepository restaurantRepository;

    public ReviewService(ReviewRepository reviewRepository, RestaurantRepository restaurantRepository) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Review createReview(long restaurantId, Review review) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.RESTAURANT_NOT_FOUND));
        review.setRestaurant(restaurant);

        return reviewRepository.save(review);
    }

    public Review updateReview(long reviewId, Review review) {
        Review findReview = findReview(reviewId);

        Optional.ofNullable(review.getTitle()).ifPresent(title -> findReview.setTitle(title));
        Optional.ofNullable(review.getContent()).ifPresent(content -> findReview.setContent(content));
        Optional.ofNullable(review.getPhoto()).ifPresent(photo -> findReview.setPhoto(photo));
        Optional.ofNullable(review.getRating()).ifPresent(rating -> findReview.setRating(rating));
        findReview.setModified_at(LocalDateTime.now());

        return reviewRepository.save(findReview);
    }

    public Review findReview(long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.REVIEW_NOT_FOUND));
    }

    public Page<Review> findReviews(long restaurantId, int page, int size) {
        return reviewRepository.findByRestaurant_RestaurantIdAndStatus(restaurantId, REVIEW_VALID, PageRequest.of(page, size, Sort.by("reviewId").descending()));
    }

    public void deleteReview(long reviewId) {
        Review findReview = findReview(reviewId);

        findReview.setStatus(Review.ReviewStatus.REVIEW_DELETED);
        reviewRepository.save(findReview);
    }
}
