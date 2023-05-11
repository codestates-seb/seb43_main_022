package com.codea.location;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class LocationDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotBlank
        private double latitude;
        @NotBlank
        private double longitude;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long locationId;
        private double latitude;
        private double longitude;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long locationId;
        private double latitude;
        private double longitude;
    }
}
