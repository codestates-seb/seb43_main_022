package com.codea.tag;

import com.codea.restaurant.Restaurant;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
public class TagRestaurantDto {
    @Getter
    public static class Post {
        private String name;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
//        private long tagId;
//        private String name;
        private TagDto.TagResponse tag;
    }


}
