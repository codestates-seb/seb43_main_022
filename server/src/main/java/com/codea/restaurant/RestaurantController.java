package com.codea.restaurant;

import com.codea.address.Address;
import com.codea.address.AddressDto;
import com.codea.address.AddressMapper;
import com.codea.category.CategoryMapper;
import com.codea.common.response.MultiResponseDto;
import com.codea.common.utils.UriCreator;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
@Validated
@Transactional
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final RestaurantMapper mapper;
    private final AddressMapper addressMapper;
    private final CategoryMapper categoryMapper;

    public RestaurantController(RestaurantService restaurantService, RestaurantMapper mapper, AddressMapper addressMapper, CategoryMapper categoryMapper) {
        this.restaurantService = restaurantService;
        this.mapper = mapper;
        this.addressMapper = addressMapper;
        this.categoryMapper = categoryMapper;
    }

    @Transactional
    @PostMapping
    public ResponseEntity postRestaurant(@Valid @RequestBody RestaurantDto.Post requestBody, @AuthenticationPrincipal String email) {

//        System.out.println(email+ "1@@@@@@@@@@@@@@@@@@@");

        AddressDto.Post addressDto = new AddressDto.Post(requestBody.getStreetAddress(), requestBody.getLatitude(), requestBody.getLongitude());
        Address address = addressMapper.addressPostDtoToAddress(addressDto);

//        CategoryDto.Post categoryDto = new CategoryDto.Post(requestBody.getCategory().getName());
//        Category category = categoryMapper.categoryPostDtoToCategory(categoryDto);

//        Restaurant restaurant = restaurantService.createRestaurant(email, mapper.restaurantPostDtoToRestaurant(requestBody));
        Restaurant restaurant = restaurantService.createRestaurant(email, address, requestBody);

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
//        MenuDto.Post menuDto = new List<MenuDto.Post> (requestBody.getMenu());

        Restaurant restaurant = restaurantService.updateRestaurant(restaurantId, email, address, requestBody);

        return new ResponseEntity<>(mapper.restaurantToRestaurantResponseDto(restaurant), HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/{restaurant-id}")
    public ResponseEntity getRestaurant(@PathVariable("restaurant-id") long restaurantId) {
        Restaurant restaurant = restaurantService.findRestaurant(restaurantId);
//        double averageRating = restaurant.calculateAverageRating();

//        restaurant.setAverageRating(averageRating);
//        restaurant.setRating(averageRating);
        RestaurantDto.Response responseDto = mapper.restaurantToRestaurantResponseDto(restaurant);
        //SingleResponseDto<RestaurantDto.Response> singleResponseDto = new SingleResponseDto<>(responseDto);
        // responseDto.setAverageRating(averageRating);
        return new ResponseEntity<>(responseDto , HttpStatus.OK);
    }

    /*private double calculateAverageRating(Restaurant restaurant){
        List<Review> reviews =restaurant.getReviews();
        if(reviews.isEmpty()){
            return 0;
        }
        int totalScore = 0;
        for (Review review : reviews){
            totalScore += review.getRating().getScore();
        }
        return (double) totalScore / reviews.size();
    }*/

//    @GetMapping("/{coffee-id}")
//    public ResponseEntity getCoffee(@PathVariable("coffee-id") long coffeeId) {
//        Coffee coffee = coffeeService.findCoffee(coffeeId);
//
//        return new ResponseEntity<>(
//                new SingleResponseDto<>(mapper.coffeeToCoffeeResponseDto(coffee)),
//                HttpStatus.OK);
//    }

    @Transactional
    @GetMapping
    public ResponseEntity getRestaurants(@Positive @RequestParam(value = "page", required = false) Integer page,
                                         @Positive @RequestParam(value = "size", required = false) Integer size) {
        if (page == null) page = 1;
        if (size == null) size = 8;
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

    @Transactional
    @GetMapping("/search")
    public ResponseEntity searchRestaurants(@RequestParam(value = "keyword", name = "keyword") String keyword,
                                            @RequestParam(value = "page", required = false) Integer page,
                                            @RequestParam(value = "size", required = false) Integer size) {

        if (page == null) page = 1;
        if (size == null) size = 4;
        Page<Restaurant> restaurantPage = restaurantService.searchRestaurants(keyword,page - 1, size);
        List<Restaurant> restaurants = restaurantPage.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.restaurantToRestaurantResponseDtos(restaurants), restaurantPage), HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/category")
    public ResponseEntity searchCategory(@RequestParam(value = "name", name = "name") String name,
                                            @RequestParam(value = "page", required = false) Integer page,
                                            @RequestParam(value = "size", required = false) Integer size) {
        if (page == null) page = 1;
        if (size == null) size = 4;
        Page<Restaurant> restaurantPage = restaurantService.searchByCategory(name,page - 1, size);
        List<Restaurant> restaurants = restaurantPage.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.restaurantToRestaurantResponseDtos(restaurants), restaurantPage), HttpStatus.OK);
    }


//    @GetMapping("/search")
//    public ResponseEntity searchByTag(@PathVariable("url") String url,
//                                      @RequestParam(value = "tag") String tag,
//                                      @RequestParam(value = "page", required = false) Integer page,
//                                      @RequestParam(value = "size", required = false) Integer size) {
//
//        if (page == null) page = 1;
//        if (size == null) size = 4;
//        Page<Restaurant> restaurantPage = restaurantService.searchByTagRestaurants(page - 1, size, url, tag);
//        List<Restaurant> restaurants = restaurantPage.getContent();
//
//        return new ResponseEntity<>(
//                new MultiResponseDto<>(mapper.restaurantToRestaurantResponseDtos(restaurants), restaurantPage), HttpStatus.OK);
//    }
}









