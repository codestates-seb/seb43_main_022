package com.codea.location;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

public class LocationDto {
    @Getter
    public static class Post {
        @NotNull
        private double latitude;
        @NotNull
        private double longitude;
        @NotNull
        private long addressId;
    }

    @Getter
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
