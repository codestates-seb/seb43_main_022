package com.codea.photo;

import com.codea.tag.Tag;
import com.codea.tag.TagDto;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface PhotoMapper {
    Photo photoPostDtoToPhoto(PhotoDto.Post photoPostDto);
    Photo photoPatchDtoToPhoto(PhotoDto.Patch photoPatchDto);
    PhotoDto.Response photoToPhotoResponseDto(Photo photo);

    List<PhotoDto.Response> photosToPhotoResponseDto(List<Photo> photo);
}
