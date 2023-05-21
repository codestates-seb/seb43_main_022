package com.codea.Image;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ImageDto {
    private String title;
    private String url;
    private MultipartFile file;
}
