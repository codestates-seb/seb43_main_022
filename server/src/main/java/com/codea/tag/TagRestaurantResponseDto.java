package com.codea.tag;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TagRestaurantResponseDto {
    private long restaurantId;
    private long tagId;
}
