package com.codea.review;

import com.codea.member.Member;
import com.codea.response.MultiResponseDto;
import com.codea.restaurant.Restaurant;
import com.codea.restaurant.RestaurantService;
import com.codea.utils.UriCreator;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reviews")
@Validated
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMapper mapper;
    private final RestaurantService restaurantService;
    //private final ReviewRepository reviewRepository;

    public ReviewController(ReviewService reviewService, ReviewMapper mapper, ReviewRepository reviewRepository, RestaurantService restaurantService) {
        this.reviewService = reviewService;
        this.mapper = mapper;
        // this.reviewRepository = reviewRepository;
        this.restaurantService = restaurantService;
    }

    @PostMapping("/restaurants/{restaurant-id}")
    public ResponseEntity postReview(@PathVariable("restaurant-id") @Positive long restaurantId,
                                     @Valid @RequestBody ReviewDto.Post requestBody,
                                     @AuthenticationPrincipal String email) {
       /* Review review = reviewService.createReview(restaurantId, email, mapper.reviewPostDtoToReview(requestBody));

        String ReviewUrl = "/restaurants/" + restaurantId + "/reviews";
        URI location = UriCreator.createUri(ReviewUrl, review.getReviewId());

        return ResponseEntity.created(location).build();*/
        Restaurant restaurant = restaurantService.findRestaurant(restaurantId);
        if (restaurant == null) {
            return ResponseEntity.notFound().build();
        }

        Review review = mapper.reviewPostDtoToReview(requestBody);
        review.setRestaurant(restaurant);

        reviewService.createReview (restaurantId, email,review);

        String reviewUrl = "/restaurants/" + restaurantId + "/reviews/" + review.getReviewId();
        URI location = UriComponentsBuilder.fromPath(reviewUrl).build().toUri();

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{review-id}")
    public ResponseEntity patchReview(@PathVariable("review-id") @Positive long reviewId,
                                      @Valid @RequestBody ReviewDto.Patch requestBody,
                                      @AuthenticationPrincipal String email) {
        Review review = reviewService.updateReview(reviewId, email, mapper.reviewPatchDtoToReview(requestBody));

        return new ResponseEntity<>(mapper.reviewToReviewResponseDto(review),HttpStatus.OK);
    }

    @GetMapping("/{review-id}")
    public ResponseEntity getReview(@PathVariable("review-id") @Positive long reviewId
            /* @PathVariable("member") Member member*/ ) {
        Review review = reviewService.findReview(reviewId);
        /*List<Review> reviews = reviewRepository.findByMember(member);

        int totalPoints = 0;
        int totalReviews = reviews.size();

        for (Review review1 : reviews) {
            if (review1.getRating)().equals("맛있어요") {
                totalPoints += 5;
            } else if (review1.getRating().equals("별로에요")) {
                totalPoints += 1;
            }
        }
        if (totalReviews > 0) {
            return (double) totalPoints / totalReviews;
        }
        else {
            return 0.0;
        } */

        return new ResponseEntity<>(mapper.reviewToReviewResponseDto(review),HttpStatus.OK);
    }

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

    @DeleteMapping("/{review-id}")
    public ResponseEntity deleteReview(@PathVariable("review-id") @Positive long reviewId) {
        reviewService.deleteReview(reviewId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}