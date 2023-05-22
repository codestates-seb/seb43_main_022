package com.codea.Image;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;
    private final ImageService imageService;
    private final ImageMapper mapper;

//    @GetMapping("/upload")
//    public String goToUpload() {
//        return "upload";
//    }

    @PostMapping("/upload")
    public ResponseEntity uploadFile(ImageDto.Post imageDto) throws IOException {
        String url = s3Service.uploadFile(imageDto.getImage());

        imageDto.setUrl(url);
        imageService.saveImageUrl(imageDto);

        return new ResponseEntity(HttpStatus.CREATED);
    }

//    @GetMapping("/list")
//    public String listPage(Model model) {
//        List<FileEntity> fileList =fileService.getFiles();
//        model.addAttribute("fileList", fileList);
//        return "list";
//    }
}