package com.codea.tag;

import com.codea.restaurant.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TagMapper {
//    default Tag tagPostDtoToTag(TagDto.Post tagPostDto) {
//        Tag tag = new Tag();
//        List<TagRestaurant> tagRestaurants = tagPostDto.getTagRestaurants().stream()
//                .map(tagRestaurantDto -> {
//                    TagRestaurant tagRestaurant = new TagRestaurant();
//                    Restaurant restaurant = new Restaurant();
//                    restaurant.setRestaurantId(tagRestaurantDto.getRestaurantId());
//                    tagRestaurant.setTag(tag);
//                    tagRestaurant.setRestaurant(restaurant);
//                    return tagRestaurant;
//                }).collect(Collectors.toList());
//        tag.setTagRestaurants(tagRestaurants);
//
//        return tag;
//    }
    Tag tagPostDtoToTag(TagDto.Post tagPostDto) ;
    Tag tagPatchDtoToTag(TagDto.Patch tagPatchDto);
    TagDto.Response tagToTagResponseDto(Tag tag);

    List<TagDto.Response> tagsToTagResponseDto(List<Tag> tag);

    TagRestaurantDto.Response tagRestaurantTo
}
