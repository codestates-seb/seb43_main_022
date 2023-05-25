package com.codea.review;

import com.codea.common.response.MultiResponseDto;
import com.codea.restaurant.RestaurantService;
import com.codea.common.utils.UriCreator;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reviews")
@Validated
@Transactional
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMapper mapper;


    public ReviewController(ReviewService reviewService, ReviewMapper mapper) {
        this.reviewService = reviewService;
        this.mapper = mapper;
    }
    @Transactional
    @PostMapping("/restaurants/{restaurant-id}")
    public ResponseEntity postReview(@PathVariable("restaurant-id") @Positive long restaurantId,
                                     @Valid @RequestBody ReviewDto.Post requestBody,
                                     @AuthenticationPrincipal String email) {

       Review review = reviewService.createReview(restaurantId, email, requestBody);

        String ReviewUrl = "/restaurants/" + restaurantId + "/reviews";
        URI location = UriCreator.createUri(ReviewUrl, review.getReviewId());

        return ResponseEntity.created(location).build();
    }
    @Transactional
    @PatchMapping("/{review-id}")
    public ResponseEntity patchReview(@PathVariable("review-id") @Positive long reviewId,
                                      @Valid @RequestBody ReviewDto.Patch requestBody,
                                      @AuthenticationPrincipal String email) {
        Review review = reviewService.updateReview(reviewId, email, requestBody);

        return new ResponseEntity<>(mapper.reviewToReviewResponseDto(review),HttpStatus.OK);
    }
    @Transactional
    @GetMapping("/{review-id}")
    public ResponseEntity getReview(@PathVariable("review-id") @Positive long reviewId) {
        Review review = reviewService.findReview(reviewId);

        return new ResponseEntity<>(mapper.reviewToReviewResponseDto(review),HttpStatus.OK);
    }
    @Transactional
    @GetMapping("/restaurants/{restaurant-id}")
    public ResponseEntity getReviews(@PathVariable("restaurant-id") @Positive long restaurantId,
                                     @Positive @RequestParam(value = "page", required = false) Integer page,
                                     @Positive @RequestParam(value = "size", required = false) Integer size) {
        if(page == null) page = 1;
        if(size == null) size = 10;
        Page<Review> reviewPage = reviewService.findReviews(restaurantId,page - 1, size);
        List<Review> review = reviewPage.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.reviewToReviewResponseDto(review), reviewPage), HttpStatus.OK);
    }
    @Transactional
    @DeleteMapping("/{review-id}")
    public ResponseEntity deleteReview(@PathVariable("review-id") @Positive long reviewId) {
        reviewService.deleteReview(reviewId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}