package com.codea.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor
public class AddressDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotBlank
        private String streetAddress;
        private double latitude;
        private double longitude;

    }

    @Getter
    public static class Patch {
        private long addressId;
        private String streetAddress;
        private double latitude;
        private double longitude;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long addressId;
        private String streetAddress;
        private double latitude;
        private double longitude;
    }

}
