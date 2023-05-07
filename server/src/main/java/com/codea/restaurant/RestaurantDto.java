package com.codea.restaurant;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Setter
@Getter
public class RestaurantDto {
    public class Post {
        @NotBlank
        private String name;
        @NotBlank
        private String content;
        @NotBlank
        private String location;
        @NotBlank
        private String tel;
        @NotBlank
        private byte[] photo;
        @NotBlank
        private String open_time;
        private LocalDateTime created_at;
        private LocalDateTime modified_at;
        private int total_views;
        private int total_favorite;
        private double rating;
    }

    public static class Patch {
        @Positive
        private long restaurantId;
        @NotBlank
        private String name;
        @NotBlank
        private String content;
        @NotBlank
        private String location;
        @NotBlank
        private String tel;
        @NotBlank
        private byte[] photo;
        @NotBlank
        private String opentime;
        private LocalDateTime created_at;
        private LocalDateTime modified_at;
        private int total_views;
        private int total_favorite;
        private double rating;

        public void setRestaurantId(long restaurantId) {
            this.restaurantId = restaurantId;
        }
    }

    public static class Response {
        @Positive
        private long restaurantId;
        @NotBlank
        private String name;
        @NotBlank
        private String content;
        @NotBlank
        private String location;
        @NotBlank
        private String tel;
        @NotBlank
        private byte[] photo;
        @NotBlank
        private String opentime;
        private LocalDateTime created_at;
        private LocalDateTime modified_at;
        private int total_views;
        private int total_favorite;
        private double rating;
    }
}
