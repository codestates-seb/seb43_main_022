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
    @Column(nullable = false,updatable = false,unique = true,length = 20)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private CategoryStatus categoryStatus = CategoryStatus.SALAD;

    public enum CategoryStatus{
        SALAD("샐러드"),
        WESTERN_FOOD("양식"),
        JAPANESE_FOOD("일식"),
        CHINESE_FOOD("중식"),
        VIETNAMESE_FOOD("베트남 요리");

        @Getter
        private String status;

        CategoryStatus(String status){
            this.status = status;
        }

    }
    @OneToMany(mappedBy = "Category")
    private List<Restaurant> restaurants = new ArrayList<>();
}
