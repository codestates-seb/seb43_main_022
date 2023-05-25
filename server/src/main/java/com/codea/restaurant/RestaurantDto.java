package com.codea.restaurant;

import com.codea.Menu.MenuDto;
import com.codea.category.CategoryDto;
import com.codea.favorite.Favorite;
import com.codea.favorite.FavoriteDto;
import com.codea.member.MemberDto;
import com.codea.review.ReviewDto;
import com.codea.tag.Tag;
import com.codea.tag.TagDto;
import com.codea.tag.TagRestaurantDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

public class RestaurantDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotBlank
        private String restaurantName;
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
        private String image;
        private List<MenuDto.Post> menu;
        private CategoryDto.Post category;
        private List<TagDto.Post> tag;
        private String base64Image;
        private String imageName;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch {
        private long restaurantId;
        private String restaurantName;
        private String content;
        private String tel;
        private String open_time;
        private String image;
        private String streetAddress;
        private String detailAddress;
        private double latitude;
        private double longitude;
        private List<MenuDto.Patch> menu;
        private CategoryDto.Post category;
        private List<TagDto.Patch> tag;
        private String base64Image;
        private String imageName;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long restaurantId;
        private String restaurantName;
        private String content;
        private String tel;
        private String open_time;
        private String image;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private int total_views;
        private int total_reviews;
        private int totalFavorite;
        private double rating;
        private List<MenuDto.Response> menu;
        private List<ReviewDto.Response> reviews;
        private List<TagRestaurantDto.Response> tagRestaurants;
        private List<FavoriteDto.ResponseFavorite> favorites;
        private String category;
        private String streetAddress;
        private String detailAddress;
        private double latitude;
        private double longitude;
        private MemberDto.RestaurantResponse member;
    }

}
