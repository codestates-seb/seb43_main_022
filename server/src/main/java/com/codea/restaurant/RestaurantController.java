package com.codea.restaurant;

import com.codea.address.Address;
import com.codea.address.AddressDto;
import com.codea.address.AddressMapper;
import com.codea.member.Member;
import com.codea.member.MemberDto;
import com.codea.response.MultiResponseDto;
import com.codea.review.Review;
import com.codea.utils.UriCreator;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
@Validated
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final RestaurantMapper mapper;
    private final AddressMapper addressMapper;

    public RestaurantController(RestaurantService restaurantService, RestaurantMapper mapper, AddressMapper addressMapper) {
        this.restaurantService = restaurantService;
        this.mapper = mapper;
        this.addressMapper = addressMapper;
    }

    @PostMapping
    public ResponseEntity postRestaurant(@Valid @RequestBody RestaurantDto.Post requestBody, @AuthenticationPrincipal String email) {

//        System.out.println(email+ "1@@@@@@@@@@@@@@@@@@@");

        AddressDto.Post addressDto = new AddressDto.Post(requestBody.getStreetAddress(), requestBody.getLatitude(), requestBody.getLongitude());
        Address address = addressMapper.addressPostDtoToAddress(addressDto);

//        Restaurant restaurant = restaurantService.createRestaurant(email, mapper.restaurantPostDtoToRestaurant(requestBody));

        Restaurant restaurant = restaurantService.createRestaurant(email, address, mapper.restaurantPostDtoToRestaurant(requestBody));

        URI location = UriCreator.createUri("/restaurants", restaurant.getRestaurantId());
        return ResponseEntity.created(location).build();
    }


    @Transactional
    @PatchMapping("/{restaurant-id}")
    public ResponseEntity patchRestaurant(@PathVariable("restaurant-id") long restaurantId,
                                          @Valid @RequestBody RestaurantDto.Patch requestBody,
                                          @AuthenticationPrincipal String email) {

        AddressDto.Post addressDto = new AddressDto.Post(requestBody.getStreetAddress(), requestBody.getLatitude(), requestBody.getLongitude());
        Address address = addressMapper.addressPostDtoToAddress(addressDto);

        Restaurant restaurant = restaurantService.updateRestaurant(restaurantId, email, address, mapper.restaurantPatchDtoToRestaurant(requestBody));

        return new ResponseEntity<>(mapper.restaurantToRestaurantResponseDto(restaurant), HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/{restaurant-id}")
    public ResponseEntity getRestaurant(@PathVariable("restaurant-id") long restaurantId){
        Restaurant restaurant = restaurantService.findRestaurant(restaurantId);

        return new ResponseEntity<>(mapper.restaurantToRestaurantResponseDto(restaurant), HttpStatus.OK);
    }

    @Transactional
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

//    @GetMapping("/top10")
//    public ResponseEntity getTop10Restaurants() {
//        List<Restaurant> top10Restaurants = restaurantService.getTop10Restaurants();
//        List<RestaurantDto.Response> top10Responses = new ArrayList<>();
//
//        for (Restaurant restaurant : top10Restaurants) {
//            RestaurantDto.Response response = mapper.restaurantToRestaurantResponseDto(restaurant);
//            top10Responses.add(response);
//        }
//        return new ResponseEntity(top10Responses, HttpStatus.OK);
//    }

    @GetMapping("/top10")
    public ResponseEntity getTop10Restaurants(@Positive @RequestParam(value = "page", required = false) Integer page,
                                         @Positive @RequestParam(value = "size", required = false) Integer size) {
        if(page == null) page = 1;
        if(size == null) size = 10;
        Page<Restaurant> restaurantPage = restaurantService.getTop10Restaurants(page - 1, size);
        List<Restaurant> restaurants = restaurantPage.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.restaurantToRestaurantResponseDtos(restaurants), restaurantPage), HttpStatus.OK);
    }
}