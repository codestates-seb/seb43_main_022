package com.codea.tag;

import com.codea.restaurant.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TagMapper {

    Tag tagPostDtoToTag(TagDto.Post tagPostDto) ;
    Tag tagPatchDtoToTag(TagDto.Patch tagPatchDto);
    TagDto.Response tagToTagResponseDto(Tag tag);
    List<TagDto.Response> tagsToTagResponseDto(List<Tag> tag);

}
