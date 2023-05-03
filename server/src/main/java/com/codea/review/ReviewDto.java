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
        private byte[] photo;
        private Review.Rating rating;
    }

    @Getter
    public static class Patch {
        private long id;
        private String title;
        private String content;
        private byte[] photo;
        private Review.Rating rating;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long id;
        private String title;
        private String content;
        private byte[] photo;
        private LocalDateTime created_at;
        private LocalDateTime modified_at;
        private Review.Rating rating;
    }
}
