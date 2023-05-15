package com.codea.photo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class PhotoDto {
        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        public static  class Post{

            @NotBlank
            private String name;
        }
        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Patch{
            @Positive
            private long photoId;
            @NotBlank
            @Size(max = 50)
            private String name;

        }
        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Response{
            @Positive
            private long photoId;
            @NotBlank
            @Size(max = 50)
            private String name;
        }
    }


