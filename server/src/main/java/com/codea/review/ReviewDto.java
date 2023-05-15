package com.codea.review;

import com.codea.member.Member;
import com.codea.member.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class ReviewDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotBlank(message = "제목을 입력하세요.")
        private String title;
        @NotBlank(message = "내용을 입력하세요.")
        private String content;
        private String photo;
        private Review.Rating rating;
    }

    @Getter
    @AllArgsConstructor
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
        private MemberDto.ReviewResponse member;
    }
}
