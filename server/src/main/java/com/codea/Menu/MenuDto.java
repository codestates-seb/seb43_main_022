package com.codea.Menu;

import com.codea.review.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class MenuDto {
    @Getter
    public static class Post {
        @NotBlank(message = "이름을 입력하세요.")
        private String name;
        @NotBlank(message = "가격을 입력하세요.")
        private int price;
    }

    @Getter
    public static class Patch {
        private String name;
        private int price;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private String name;
        private int price;
    }
}
