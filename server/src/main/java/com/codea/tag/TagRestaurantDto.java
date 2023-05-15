package com.codea.tag;

import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
public class TagRestaurantDto {
    @Positive
    private long restaurantId;

}
