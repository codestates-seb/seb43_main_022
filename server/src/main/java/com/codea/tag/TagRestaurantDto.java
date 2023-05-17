package com.codea.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
public class TagRestaurantDto {
    @Getter
    public static class Post {
        @Positive
        private long restaurantId;
        private String name;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long tagId;
        private String name;
    }

}
