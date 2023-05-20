package com.codea.tag;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    public static class Post{
        @NotNull
        private String name;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch{

        private String name;

    }
    @Getter
    @AllArgsConstructor
    public static class Response{
        private List<TagRestaurantDto.Response> tagRestaurants;
    }

    @Getter
    @AllArgsConstructor
    public static class TagResponse{
        private long tagId;
        private String name;
    }

}
