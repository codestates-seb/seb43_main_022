package com.codea.restaurant;

import com.codea.response.MultiResponseDto;
import com.codea.review.Review;
import com.codea.utils.UriCreator;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
@Validated
public class RestaurantController {
    private final static String RESTAURANT_DEFAULT_URL = "/restaurants";
    private final RestaurantService restaurantService;
    private final RestaurantMapper mapper;

    public RestaurantController(RestaurantService restaurantService,RestaurantMapper mapper){
        this.restaurantService = restaurantService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postRestaurant(@Valid @RequestBody RestaurantDto.Post requestBody) {
        Restaurant restaurant = restaurantService.createRestaurant(mapper.restaurantPostDtoToRestaurant(requestBody));

        URI location = UriCreator.createUri(RESTAURANT_DEFAULT_URL, restaurant.getRestaurantId());
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{restaurant-id}")
    public ResponseEntity patchRestaurant(@PathVariable("restaurant-id") long restaurantId,
                                          @Valid @RequestBody RestaurantDto.Patch requestBody){
        Restaurant restaurant = restaurantService.updateRestaurant(restaurantId, mapper.restaurantPatchDtoToRestaurant(requestBody));

        return new ResponseEntity<>(mapper.restaurantToRestaurantResponseDto(restaurant),HttpStatus.OK);
    }

    @GetMapping("/{restaurant-id}")
    public ResponseEntity getRestaurant(@PathVariable("restaurant-id") long restaurantId){
        Restaurant restaurant = restaurantService.findRestaurant(restaurantId);

        return new ResponseEntity<>(mapper.restaurantToRestaurantResponseDto(restaurant), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getRestaurants(@Positive @RequestParam(value = "page", required = false) Integer page,
                                         @Positive @RequestParam(value = "size", required = false) Integer size) {
        if(page == null) page = 1;
        if(size == null) size = 10;
        Page<Restaurant> restaurantPage = restaurantService.findRestaurants(page - 1, size);
        List<Restaurant> restaurants = restaurantPage.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.restaurantToRestaurantResponseDtos(restaurants), restaurantPage), HttpStatus.OK);
    }

    @DeleteMapping("/{restaurant-id}")
    public ResponseEntity deleteRestaurant(@PathVariable("restaurant-id") long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
