package com.codea.restaurant.mapper;

import com.codea.restaurant.dto.RestaurantPatchDto;
import com.codea.restaurant.dto.RestaurantPostDto;
import com.codea.restaurant.dto.RestaurantResponseDto;
import com.codea.restaurant.entity.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    Restaurant restaurantPostDtoToRestaurant(RestaurantPostDto restaurantPostDto);
    Restaurant restaurantPatchDtoToRestaurant(RestaurantPatchDto restaurantPatchDto);
    RestaurantResponseDto restaurantToResponseDto(Restaurant restaurant);
}
