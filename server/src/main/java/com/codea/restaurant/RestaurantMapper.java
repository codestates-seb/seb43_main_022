package com.codea.restaurant;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    Restaurant restaurantPostDtoToRestaurant(RestaurantDto.Post restaurantPostDto);
    Restaurant restaurantPatchDtoToRestaurant(RestaurantDto.Patch restaurantPatchDto);
    RestaurantDto.Response restaurantToRestaurantResponseDto(Restaurant restaurant);
    List<RestaurantDto.Response> restaurantToRestaurantResponseDtos(List<Restaurant> restaurant);
}
