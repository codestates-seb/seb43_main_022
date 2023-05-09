package com.codea.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;


public class RestaurantDto {
    @Getter
    public static class Post {
        @NotBlank
        private String name;
        @NotBlank
        private String content;
        @NotBlank
        private String location;
        @NotBlank
        private String tel;
        @NotBlank
        private String open_time;
        private String photo;
    }

    @Getter
    public static class Patch {
        private long restaurantId;
        private String name;
        private String content;
        private String location;
        private String tel;
        private String open_time;
        private String photo;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long restaurantId;
        private String name;
        private String content;
        private String location;
        private String tel;
        private String open_time;
        private String photo;
        private LocalDateTime created_at;
        private LocalDateTime modified_at;
        private int total_views;
        private int total_reviews;
        private int total_favorite;
        private double rating;
    }
}
