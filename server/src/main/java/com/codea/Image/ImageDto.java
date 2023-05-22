package com.codea.Image;

import com.codea.address.Address;
import com.codea.favorite.FavoriteDto;
import com.codea.review.ReviewDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class ImageDto {

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long imageId;
        private String imageName;
        private String s3Url;
    }

}
