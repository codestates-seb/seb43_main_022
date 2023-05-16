package com.codea.restaurant;

import com.codea.Menu.MenuDto;
import com.codea.address.Address;
import com.codea.category.Category;
import com.codea.category.CategoryDto;
import com.codea.review.ReviewDto;
import com.codea.tag.TagDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class RestaurantDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotBlank
        private String name;
        @NotBlank
        private String content;
        @NotBlank
        private String tel;
        @NotBlank
        private String open_time;
        @NotBlank
        private String streetAddress;
        @NotBlank
        private String detailAddress;
        private double latitude;
        private double longitude;
        private String photoUrl;
        private List<MenuDto.Post> menu;
        private Category category;
        private List<TagDto.Post> tags;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long restaurant_id;
        private String name;
        private String content;
        private String tel;
        private String open_time;
        private String photoUrl;
        private String streetAddress;
        private String detailAddress;
        private double latitude;
        private double longitude;
        private List<MenuDto.Post> menu;
        private CategoryDto.Post categories;
        private List<TagDto.Post> tags;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long restaurant_id;
        private String name;
        private String content;
        private String tel;
        private String open_time;
        private String photoUrl;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private int total_views;
        private int total_reviews;
        private int totalFavorite;
        private double rating;
        private List<MenuDto.Response> menu;
        private List<ReviewDto.Response> reviews;
        private Address address;
    }
}
