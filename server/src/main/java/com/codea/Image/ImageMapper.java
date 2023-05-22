package com.codea.Image;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImageMapper {
    Image imagePostDtoToImage(ImageDto.Post imagePostDto);
    Image imagePatchDtoToImage(ImageDto.Patch imagePatchDto);
    ImageDto.Response imageToImageResponseDto(Image image);
}

