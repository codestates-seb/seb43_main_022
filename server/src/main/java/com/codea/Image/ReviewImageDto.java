package com.codea.Image;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ReviewImageDto {

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long imageId;
        private String imageName;
        private String s3Url;
    }

}
