package com.codea.restaurant;

import com.codea.Image.ImageService;
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
    private final ImageService imageService;


    public RestaurantController(RestaurantService restaurantService, RestaurantMapper mapper,
                                AddressMapper addressMapper, CategoryMapper categoryMapper, ImageService imageService) {
        this.restaurantService = restaurantService;
        this.mapper = mapper;
        this.addressMapper = addressMapper;
        this.categoryMapper = categoryMapper;
        this.imageService = imageService;
    }


    @Transactional
    @PostMapping
    public ResponseEntity postRestaurant(@Valid @RequestBody RestaurantDto.Post requestBody, @AuthenticationPrincipal String email) {


        AddressDto.Post addressDto = new AddressDto.Post(requestBody.getStreetAddress(), requestBody.getLatitude(), requestBody.getLongitude());
        Address address = addressMapper.addressPostDtoToAddress(addressDto);

        String imageUrl = "https://main022.s3.ap-northeast-2.amazonaws.com/image/default_restaurant.png";
        if (requestBody.getBase64Image() != null && requestBody.getImageName() != null && !(requestBody.getBase64Image().isEmpty()) && !(requestBody.getImageName().isEmpty())) {
            imageUrl = imageService.uploadImage(requestBody.getImageName(), requestBody.getBase64Image(), email);
        }

        Restaurant restaurant = restaurantService.createRestaurant(email, address, requestBody, imageUrl);

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

        String imageUrl = "https://main022.s3.ap-northeast-2.amazonaws.com/image/default_restaurant.png";
        if (requestBody.getBase64Image() != null && requestBody.getImageName() != null && !(requestBody.getBase64Image().isEmpty()) && !(requestBody.getImageName().isEmpty())) {
            imageUrl = imageService.uploadImage(requestBody.getImageName(), requestBody.getBase64Image(), email);
        }

        Restaurant restaurant = restaurantService.updateRestaurant(restaurantId, email, address, requestBody, imageUrl);

        return new ResponseEntity<>(mapper.restaurantToRestaurantResponseDto(restaurant), HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/{restaurant-id}")
    public ResponseEntity getRestaurant(@PathVariable("restaurant-id") long restaurantId,
                                        @AuthenticationPrincipal String email) {
        Restaurant restaurant = restaurantService.findRestaurant(restaurantId);
        RestaurantDto.Response responseDto = mapper.restaurantToRestaurantResponseDto(restaurant);

        return new ResponseEntity<>(responseDto , HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/{restaurant-id}/detail")
    public ResponseEntity getDetailRestaurant(@PathVariable("restaurant-id") long restaurantId,
                                        @AuthenticationPrincipal String email) {
        Restaurant restaurant = restaurantService.findRestaurant(restaurantId);
        restaurantService.updateView(restaurant);
        RestaurantDto.Response responseDto = mapper.restaurantToRestaurantResponseDto(restaurant);

        return new ResponseEntity<>(responseDto , HttpStatus.OK);
    }


    @Transactional
    @GetMapping
    public ResponseEntity getRestaurants(@Positive @RequestParam(value = "page", required = false) Integer page,
                                         @Positive @RequestParam(value = "size", required = false) Integer size) {

        if (page == null && size == null) {
            List<Restaurant> restaurants = restaurantService.findAllRestaurants();
            return new ResponseEntity<>(mapper.restaurantToRestaurantResponseDtos(restaurants), HttpStatus.OK);
        }
        Page<Restaurant> restaurantPage = restaurantService.findRestaurants(page, size);
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
    public ResponseEntity searchRestaurants(@RequestParam(value = "keyword", name = "keyword") String keyword) {

        List<Restaurant> restaurants = restaurantService.searchRestaurants(keyword);

        return new ResponseEntity<>(mapper.restaurantToRestaurantResponseDtos(restaurants), HttpStatus.OK);
    }

//    @Transactional
//    @GetMapping("/category")
//    public ResponseEntity searchCategory(@RequestParam(value = "name", name = "name") String name,
//                                            @RequestParam(value = "page", required = false) Integer page,
//                                            @RequestParam(value = "size", required = false) Integer size) {
//        if (page == null) page = 1;
//        if (size == null) size = 4;
//        Page<Restaurant> restaurantPage = restaurantService.searchByCategory(name,page - 1, size);
//        List<Restaurant> restaurants = restaurantPage.getContent();
//
//        return new ResponseEntity<>(
//                new MultiResponseDto<>(mapper.restaurantToRestaurantResponseDtos(restaurants), restaurantPage), HttpStatus.OK);
//    }


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









