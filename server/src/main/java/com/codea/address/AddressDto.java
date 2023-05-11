package com.codea.address;

import com.codea.location.LocationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class AddressDto {
    @Getter
    public static class Post {
        @NotBlank
        private String streetAddress;

    }

    @Getter
    public static class Patch {
        private long addressId;
        private String streetAddress;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long addressId;
        private String streetAddress;
        private LocationDto.Response location;
    }
}
