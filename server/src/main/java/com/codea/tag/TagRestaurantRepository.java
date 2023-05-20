package com.codea.tag;

import com.codea.Menu.Menu;
import com.codea.restaurant.Restaurant;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TagRestaurantRepository extends JpaRepository<TagRestaurant, Long> {
    Optional<TagRestaurant> findByTag_TagId(long tagId);
    void deleteAllByRestaurant(Restaurant restaurant);
    void deleteAllByRestaurant_RestaurantId(Long restaurantId);

}