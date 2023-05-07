package com.codea.restaurant;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    Restaurant restaurantPostDtoToRestaurant(RestaurantDto.Post restaurantPostDto);
    Restaurant restaurantPatchDtoToRestaurant(RestaurantDto.Patch restaurantPatchDto);
    RestaurantDto.Response restaurantToResponseDto(Restaurant restaurant);
    List<RestaurantDto.Response> restaurantToResponseDtos(List<Restaurant> restaurant);
}
