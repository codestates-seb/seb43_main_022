package com.codea.favorite;


import com.codea.response.MultiResponseDto;
import com.codea.restaurant.Restaurant;

import com.codea.restaurant.RestaurantRepository;
import com.codea.utils.UriCreator;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import javax.validation.constraints.Positive;
import java.net.URI;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {


    private final FavoriteService favoriteService;
    private final FavoriteMapper mapper;
    private final FavoriteRepository favoriteRepository;
    private final RestaurantRepository restaurantRepository;

    public FavoriteController(FavoriteService favoriteService, FavoriteMapper mapper, FavoriteRepository favoriteRepository,
                              RestaurantRepository restaurantRepository) {
        this.favoriteService = favoriteService;
        this.mapper = mapper;
        this.favoriteRepository = favoriteRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping("restaurant/{restaurant-id}")
    public ResponseEntity postFavorite(@PathVariable("restaurant-id") @Positive long restaurantId,
                                       @AuthenticationPrincipal String email) {
        Favorite favorite = favoriteService.createFavorite(restaurantId, email);

        URI location = UriCreator.createUri("/favorites", favorite.getFavoriteId());

        System.out.println(email);
        System.out.println(restaurantId);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        return ResponseEntity.created(location).build();
    }

    @GetMapping("restaurant/{restaurant-id}")
    public ResponseEntity getFavorite(@PathVariable("restaurant-id") @Positive long restaurantId) {
        Favorite favorite = favoriteService.findFavorite(restaurantId);

        return new ResponseEntity<>(mapper.favoriteToFavoriteResponseDto(favorite),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getFavorites(@AuthenticationPrincipal String email,
                                      @Positive @RequestParam(value = "page", required = false) Integer page,
                                      @Positive @RequestParam(value = "size", required = false) Integer size) {
        if(page == null) page = 1;
        if(size == null) size = 10;
        Page<Favorite> favoritePage = favoriteService.findFavorites(email,page - 1, size);
        List<Favorite> favorites = favoritePage.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.favoritesToFavoriteResponseDto(favorites), favoritePage), HttpStatus.OK);
    }

    @DeleteMapping("/{favorite-id}")
    public void deleteFavorite(long favoriteId) {
        Favorite favorite = favoriteService.findFavorite(favoriteId);
        Restaurant restaurant = favorite.getRestaurant();

        favoriteRepository.delete(favorite);

        int count = favoriteRepository.countByRestaurant_RestaurantId(restaurant.getRestaurantId());
        restaurant.setTotalFavorite(count);

        restaurantRepository.save(restaurant);
    }
}
