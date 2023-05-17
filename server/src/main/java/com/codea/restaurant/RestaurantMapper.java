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
    @Mapping(target = "streetAddress", expression = "java(restaurant.getAddress().getStreetAddress())")
    @Mapping(target = "latitude", expression = "java(restaurant.getAddress().getLatitude())")
    @Mapping(target = "longitude", expression = "java(restaurant.getAddress().getLongitude())")
    @Mapping(target = "category", expression = "java(restaurant.getCategory().getName())")
    RestaurantDto.Response restaurantToRestaurantResponseDto(Restaurant restaurant);
    @Mapping(target = "streetAddress", expression = "java(restaurant.getAddress().getstreetAddress())")
    @Mapping(target = "latitude", expression = "java(restaurant.getAddress().getlatitude())")
    @Mapping(target = "longitude", expression = "java(restaurant.getAddress().getlongitude())")
    @Mapping(target = "category", expression = "java(restaurant.getCategory().getName())")
    List<RestaurantDto.Response> restaurantToRestaurantResponseDtos(List<Restaurant> restaurant);
}
