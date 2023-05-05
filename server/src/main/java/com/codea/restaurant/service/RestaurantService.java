package com.codea.restaurant.service;

import com.codea.restaurant.entity.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RestaurantService {
    public Restaurant createRestaurant(Restaurant restaurant){
        Restaurant createdRestaurant = restaurant;
        return createdRestaurant;
    }
    public Restaurant updateRestaurant(Restaurant restaurant){
        Restaurant updatedRestaurant = restaurant;
        return updatedRestaurant;
    }
    public Restaurant findRestaurant(long restaurantId){
        Restaurant restaurant =
                new Restaurant(restaurantId,entitydummydata);
        return restaurant;
    }
    public List<Restaurant> findRestaurants(){
        List<Restaurant> restaurants = List.of(new Restaurant(1,entitydummydata),
                new Restaurant(2,entitydummydata));
        return restaurants;
    }
    public void deleteRestaurant(long restaurantId){

    }
}
