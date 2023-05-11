package com.codea.review;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class ReviewDto {
    @Getter
    public static class Post {
        @NotBlank(message = "제목을 입력하세요.")
        private String title;
        @NotBlank(message = "내용을 입력하세요.")
        private String content;
        private String photo;
        private Review.Rating rating;
    }

    @Getter
    public static class Patch {
        private long reviewId;
        private String title;
        private String content;
        private String photo;
        private Review.Rating rating;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long reviewId;
        private String title;
        private String content;
        private String photo;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private Review.Rating rating;
        private Long memberId;
        private String memberNickName;
    }
}
