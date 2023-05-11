package com.codea.tag;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {
    Tag tagPostDtoToTag(TagDto.Post tagPostDto);
    Tag tagPatchDtoToTag(TagDto.Patch tagPatchDto);
    TagDto.Response tagToTagResponseDto(Tag tag);
}
