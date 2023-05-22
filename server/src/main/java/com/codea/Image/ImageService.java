package com.codea.Image;

import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void saveImageUrl(ImageDto.Post imageDto) {
        Image image = new Image(imageDto.getTitle(), imageDto.getUrl());
        imageRepository.save(image);
    }

//    public List<Image> getFiles() {
//        List<Image> all = imageRepository.findAll();
//        return all;
//    }
}