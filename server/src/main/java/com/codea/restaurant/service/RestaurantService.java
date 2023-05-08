package com.codea.restaurant.service;
import com.codea.exception.BusinessLogicException;
import com.codea.exception.ExceptionCode;
import com.codea.restaurant.entity.Restaurant;
import com.codea.restaurant.repository.RestaurantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }
    public Restaurant createRestaurant(Restaurant restaurant){
       verifyExistsName(restaurant.getName());

       return restaurantRepository.save(restaurant);
    }
    public Restaurant updateRestaurant(Restaurant restaurant){
       Restaurant findRestaurant = findVerifiedRestaurant(restaurant.getRestaurantId());

       Optional.ofNullable(restaurant.getName())
               .ifPresent(name->findRestaurant.setName(name));
       Optional.ofNullable(restaurant.getContent())
               .ifPresent(content-> findRestaurant.setContent(content));
       Optional.ofNullable(restaurant.getLocation())
               .ifPresent(location-> findRestaurant.setLocation(location));
       Optional.ofNullable(restaurant.getTel())
               .ifPresent(tel-> findRestaurant.setTel(tel));
      /* Optional.ofNullable(restaurant.getPhoto())
               .ifPresent(photo-> findRestaurant.setPhoto(photo)); */
       Optional.ofNullable(restaurant.getOpentime())
               .ifPresent(opentime-> findRestaurant.setOpentime(opentime));
       findRestaurant.setModifiedAt(LocalDateTime.now());

       return restaurantRepository.save(findRestaurant);
    }
    public Restaurant findRestaurant(long restaurantId){
       return findVerifiedRestaurant(restaurantId);
    }
    public Page<Restaurant> findRestaurants(int page, int size){
        return restaurantRepository.findAll(PageRequest.of(page, size,
                Sort.by("restaurantId").descending()));

    }
    public void deleteRestaurant(long restaurantId){
        Restaurant findRestaurant = findVerifiedRestaurant(restaurantId);
        restaurantRepository.delete(findRestaurant);
    }
    public Restaurant findVerifiedRestaurant (long restaurantId){
        Optional<Restaurant> optionalRestaurant =
                restaurantRepository.findById(restaurantId);
        Restaurant findRestaurant =
                optionalRestaurant.orElseThrow(()->
                        new BusinessLogicException(ExceptionCode.RESTAURANT_NOT_FOUND));
        return  findRestaurant;
    }
    private void verifyExistsName(String name){
        Optional<Restaurant> restaurant = restaurantRepository.findByName(name);
        if (restaurant.isPresent())
            throw new BusinessLogicException(ExceptionCode.RESTAURANT_EXISTS);
    }
}
