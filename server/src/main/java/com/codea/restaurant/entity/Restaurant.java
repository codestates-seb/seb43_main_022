package com.codea.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Restaurant {

    @Positive
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long restaurantId;
    @Column(nullable = false,unique = true)
    @NotBlank
    private String name;
    @Column(nullable = false,unique = true)
    @NotBlank
    private String content;
    @Column(nullable = false,unique = true)
    @NotBlank
    private String location;
    @Column(nullable = false,unique = true)
    @NotBlank
    private String tel;
   /* @Column(nullable = false,unique = true)
    @NotBlank
    private byte[] photo; */
    @Column(nullable = false)
    @NotBlank
    private String opentime;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime modifiedAt = LocalDateTime.now();
    private int total_views;
    private int total_favorite;
    private double rating;// 평점이니까 decimal을 java.math.BigDecimal double 타입으로 변환이 아니라 double 변환해줘도괜찮.....?

}
