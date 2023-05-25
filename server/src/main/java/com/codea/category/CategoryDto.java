package com.codea.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


public class CategoryDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotBlank
        private String name;
    }

    @Getter
    @AllArgsConstructor
    public  static class Response{
        private long categoryId;
        private String name;
    }
}
