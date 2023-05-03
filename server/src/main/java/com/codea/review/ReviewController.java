package com.codea.review;

import com.codea.response.MultiResponseDto;
import com.codea.utils.UriCreator;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reviews")
@Validated
public class ReviewController {
    private final static String REVIEW_DEFAULT_URL = "/reviews";
    private final ReviewService reviewService;

    private final ReviewMapper mapper;

    public ReviewController(ReviewService reviewService, ReviewMapper mapper) {
        this.reviewService = reviewService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postReview(@Valid @RequestBody ReviewDto.Post requestBody) {
        Review review = reviewService.createReview(mapper.reviewPostDtoToReview(requestBody));

        URI location = UriCreator.createUri(REVIEW_DEFAULT_URL, review.getId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchReview(@PathVariable("id") @Positive long id,
                                      @Valid @RequestBody ReviewDto.Patch requestBody) {
        Review review = reviewService.updateReview(id, mapper.reviewPatchDtoToReview(requestBody));

        return new ResponseEntity<>(mapper.reviewToReviewResponseDto(review),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getReview(@PathVariable("id") @Positive long id) {
        Review review = reviewService.findReview(id);

        return new ResponseEntity<>(mapper.reviewToReviewResponseDto(review),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getReviews(@Positive @RequestParam(value = "page", required = false) Integer page,
                                     @Positive @RequestParam(value = "size", required = false) Integer size) {
        if(page == null) page = 1;
        if(size == null) size = 10;
        Page<Review> reviewPage = reviewService.findReviews(page - 1, size);
        List<Review> review = reviewPage.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.reviewToReviewResponseDto(review), reviewPage), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteReview(@PathVariable("id") @Positive long id) {
        reviewService.deleteReview(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
