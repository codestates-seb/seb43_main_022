package com.codea.Image;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ImageDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        private String imageName;
        private String image;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long imageId;
        private String imageName;
        private String image;
    }

}
