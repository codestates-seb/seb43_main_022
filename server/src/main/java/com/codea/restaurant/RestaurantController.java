package com.codea.restaurant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity postRestaurant(@Valid @RequestBody RestaurantDto.Post requestBody){
        return new ResponseEntity<>(requestBody, HttpStatus.CREATED);
    }

    @PatchMapping("/{restaurantId}")
    public ResponseEntity patchRestaurant(@PathVariable("restaurantId") long restaurantId,
                                          @Valid@RequestBody RestaurantDto.Patch requestBody){
        requestBody.setRestaurantId(restaurantId);
        // 여기는 나중에 mapper,entity나 service클래스를 만들고 채울수 있을듯?
        return new ResponseEntity<>(requestBody, HttpStatus.OK);
    }
    @GetMapping("/{restaurantId}")
    public ResponseEntity getRestaurant(@PathVariable("restaurantId") long restaurantId){
        System.out.println("# restaurantId: ");

        return  new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity getRestaurants(){
        System.out.println("# get Restaurants");

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{restaurantId}")
    public ResponseEntity deleteRestaurant(@PathVariable("restaurantId") long restaurantId){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
