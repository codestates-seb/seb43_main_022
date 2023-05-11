package com.codea.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TagDto {
    @Getter
    public static  class Post{
        @NotBlank
        @Size(max = 30)
        private String name;
    }
    @Getter
    @Setter
    public static class Patch{

        private long tagId;
        @NotBlank
        @Size(max = 30)
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
