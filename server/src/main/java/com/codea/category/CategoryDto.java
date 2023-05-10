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
    public static  class Post {
        @NotBlank
        @Size(max = 20)
        private String name;
    }
    @Getter
    @Setter
    public  static  class  Patch{

        private long categoryId;
        @NotBlank
        @Size(max = 20)
        private String name;

    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public  static class Response{

        private long categoryId;
        @NotBlank
        @Size(max = 20)
        private String name;
    }
}
