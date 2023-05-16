package com.codea.restaurant;

import com.codea.Menu.MenuMapper;
import com.codea.address.AddressMapper;
import com.codea.review.ReviewMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
//@Mapper(componentModel = "spring", uses = {MenuMapper.class, ReviewMapper.class, AddressMapper.class})
public interface RestaurantMapper {
    Restaurant restaurantPostDtoToRestaurant(RestaurantDto.Post restaurantPostDto);
    Restaurant restaurantPatchDtoToRestaurant(RestaurantDto.Patch restaurantPatchDto);
    RestaurantDto.Response restaurantToRestaurantResponseDto(Restaurant restaurant);
    List<RestaurantDto.Response> restaurantToRestaurantResponseDtos(List<Restaurant> restaurant);
}
