package com.codea.restaurant;

import com.codea.Menu.MenuMapper;
import com.codea.address.AddressMapper;
import com.codea.favorite.Favorite;
import com.codea.favorite.FavoriteDto;
import com.codea.review.ReviewMapper;
import com.codea.tag.TagDto;
import com.codea.tag.TagRestaurant;
import com.codea.tag.TagRestaurantDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    @Mapping(target = "category", expression = "java(restaurant.getCategory())")
    Restaurant restaurantPostDtoToRestaurant(RestaurantDto.Post restaurantPostDto);
    Restaurant restaurantPatchDtoToRestaurant(RestaurantDto.Patch restaurantPatchDto);
    @Mapping(target = "streetAddress", expression = "java(restaurant.getAddress().getStreetAddress())")
    @Mapping(target = "latitude", expression = "java(restaurant.getAddress().getLatitude())")
    @Mapping(target = "longitude", expression = "java(restaurant.getAddress().getLongitude())")
    @Mapping(target = "category", expression = "java(restaurant.getCategory().getName())")
    RestaurantDto.Response restaurantToRestaurantResponseDto(Restaurant restaurant);
    @Mapping(target = "streetAddress", expression = "java(restaurant.getAddress().getStreetAddress())")
    @Mapping(target = "latitude", expression = "java(restaurant.getAddress().getLatitude())")
    @Mapping(target = "longitude", expression = "java(restaurant.getAddress().getLongitude())")
    @Mapping(target = "category", expression = "java(restaurant.getCategory().getName())")
    List<RestaurantDto.Response> restaurantToRestaurantResponseDtos(List<Restaurant> restaurant);



}
