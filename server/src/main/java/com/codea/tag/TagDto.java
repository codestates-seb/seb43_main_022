package com.codea.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

public class TagDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static  class Post{
        @NotNull
        private String name;
        @NotNull
        @Valid
        private List<TagRestaurantDto.Post> tagRestaurants;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch{

        private long tagId;
        //@NotBlank
        //@Size(max = 30)
        private String name;

    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private long tagId;
        @NotBlank
        @Size(max = 30)
        private String name;
    }
}
