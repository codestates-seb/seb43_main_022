package com.codea.restaurant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
//    Page<Restaurant> findAllOrderByTotalFavorite(int totalFavorite, Pageable pageable);
    Page<Restaurant> findAllByOrderByTotalFavoriteDesc(Pageable pageable);


}
