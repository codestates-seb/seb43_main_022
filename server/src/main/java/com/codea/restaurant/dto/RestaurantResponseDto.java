package com.codea.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RestaurantResponseDto {
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
    private double rating;// 평점이니까 decimal을 java.math.BigDecimal double 타입으로 변환이 아니라 double 변환해줘도괜찮.....?
    @Positive
    private long memberId;
    @Positive
    private long menuId;
    @Positive
    private long categoryId;
}
