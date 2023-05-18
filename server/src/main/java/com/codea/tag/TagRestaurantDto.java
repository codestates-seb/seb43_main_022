package com.codea.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.util.List;

@Getter
public class TagRestaurantDto {
    @Getter
    public static class Post {
        private String name;


    }

    @Builder
    @Getter
    @Setter
    public static class Response {
        private long tagId;
//        private String tagName;
        private TagDto.TagResponse tag;

    }

}
