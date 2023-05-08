package com.codea.restaurant.controller;

import com.codea.restaurant.dto.RestaurantPatchDto;
import com.codea.restaurant.dto.RestaurantPostDto;
import com.codea.restaurant.entity.Restaurant;
import com.codea.restaurant.service.RestaurantService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.codea.restaurant.mapper.RestaurantMapper;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
@Validated
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;


    public RestaurantController(RestaurantService restaurantService,RestaurantMapper restaurantMapper){
        this.restaurantService = restaurantService;
        this.restaurantMapper =restaurantMapper;
    }
    @PostMapping
    public ResponseEntity postRestaurant(@Valid @RequestBody RestaurantPostDto restaurantPostDto){
        Restaurant restaurant = restaurantService.createRestaurant(restaurantMapper.restaurantPostDtoToRestaurant(restaurantPostDto));
        return  new ResponseEntity (restaurantPostDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{restaurantId}")
    public ResponseEntity patchRestaurant(@PathVariable("restaurantId") long restaurantId,
                                          @Valid@RequestBody RestaurantPatchDto restaurantPatchDto){
        restaurantPatchDto.setRestaurantId(restaurantId);
        Restaurant restaurant = restaurantService.updateRestaurant(restaurantMapper.restaurantPatchDtoToRestaurant(restaurantPatchDto));

        return new ResponseEntity<>(restaurantMapper.restaurantToRestaurantResponseDto(restaurant), HttpStatus.OK);
    }
    @GetMapping("/{restaurantId}")
    public ResponseEntity getRestaurant(@PathVariable("restaurantId") long restaurantId){
        Restaurant restaurant = restaurantService.findRestaurant(restaurantId);


        return  new ResponseEntity<>(restaurantMapper.restaurantToRestaurantResponseDto(restaurant),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity getRestaurants(@Positive@RequestParam int page,
                                         @Positive@RequestParam int size){
        Page<Restaurant> pageRestaurants =restaurantService.findRestaurants(page -1, size);
        List<Restaurant> restaurants = pageRestaurants.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(restaurantMapper.restaurantsToRestaurantResponseDtos(restaurants),
                        pageRestaurants), HttpStatus.OK);



    }
    @DeleteMapping("/{restaurantId}")
    public ResponseEntity deleteRestaurant(@PathVariable("restaurantId") long restaurantId){
        restaurantService.deleteRestaurant(restaurantId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
