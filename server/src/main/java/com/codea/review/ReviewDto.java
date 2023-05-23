package com.codea.review;

import com.codea.Image.ImageDto;
import com.codea.member.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        @NotBlank(message = "제목을 입력하세요.")
        private String title;
        @NotBlank(message = "내용을 입력하세요.")
        private String content;
        private List<ImageDto.Post> image;
        private Review.Rating rating;

        public Post(String title, String content, Review.Rating rating) {
            this.title = title;
            this.content = content;
            this.rating = rating;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long reviewId;
        private String title;
        private String content;
        private List<ImageDto.Post> image;
        private Review.Rating rating;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long reviewId;
        private String title;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private Review.Rating rating;
        private MemberDto.ReviewResponse member;
        private List<ImageDto.Response> image;
    }

    @Getter
    public static class MyPageResponse {
        private long reviewId;
        private long restaurantId;
        private String title;
        private String restaurantName;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public MyPageResponse(Review review) {
            this.reviewId = review.getReviewId();
            this.restaurantId = review.getRestaurant().getRestaurantId();
            this.title = review.getTitle();
            this.restaurantName = review.getRestaurant().getRestaurantName();
            this.createdAt = review.getCreatedAt();
            this.modifiedAt = review.getModifiedAt();
        }



    }
}
