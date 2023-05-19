package com.codea.category;

import com.codea.restaurant.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Restaurant> restaurants = new ArrayList<>();

//    public enum CategoryName{
//        KOREAN_FOOD("한식"),
//        WESTERN_FOOD("양식"),
//        JAPANESE_FOOD("일식"),
//        CHINESE_FOOD("중식"),
//        VIETNAMESE_FOOD("베트남 요리"),
//        INDIAN_FOOD("인도 요리"),
//        FLOUR_BASED_FOOD("분식"),
//        DESSERT("후식"),
//        FUSION_FOOD("퓨전 요리"),
//        VEGETARIAN_DIET("채식"),
//        SEAFOOD("해물"),
//        MEAT("고기");
//
//        @Getter
//        private String CategoryName;
//
//        CategoryName(String CategoryName){
//            this.CategoryName = CategoryName;
//        }
//    }
}
