package com.codea.address;

import com.codea.location.LocationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class AddressDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotBlank
        private String streetAddress;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long addressId;
        private String streetAddress;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long addressId;
        private String streetAddress;
        private List<LocationDto.Response> location;
    }
}
