package com.codea.tag;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {
    Tag tagPostDtoToTag(TagDto.Post tagPostDto);
    Tag tagPatchDtoToTag(TagDto.Patch tagPatchDto);
    TagDto.Response tagToTagResponseDto(Tag tag);

    List<TagDto.Response> tagsToTagResponseDto(List<Tag> tag);
}
