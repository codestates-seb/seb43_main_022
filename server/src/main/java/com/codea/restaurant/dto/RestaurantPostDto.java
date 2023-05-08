package com.codea.restaurant.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
@Getter
@Setter
public class RestaurantPostDto {
    @NotBlank
    private String name;
    @NotBlank
    private String content;
    @NotBlank
    private String location;
    @NotBlank
    private String tel;
    /*@NotBlank
    private byte[] photo; */
    @NotBlank
    private String opentime;
    private LocalDateTime created_at;
    private LocalDateTime modified_at;
    private int total_views;
    private int total_favorite;
    private double rating;




}
