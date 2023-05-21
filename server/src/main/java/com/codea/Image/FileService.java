//package com.codea.Image;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class FileService {
//    private final ImageRepository imageRepository;
//
//    public void save(ImageDto imageDto) {
//        Image image = new Image(imageDto.getTitle(), imageDto.getUrl());
//        imageRepository.save(image);
//    }
//
//    public List<Image> getFiles() {
//        List<Image> all = imageRepository.findAll();
//        return all;
//    }
//}