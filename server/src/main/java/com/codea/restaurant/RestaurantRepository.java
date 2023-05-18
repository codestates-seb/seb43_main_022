package com.codea.restaurant;

import com.codea.favorite.Favorite;
import com.codea.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    //    Page<Restaurant> findAllOrderByTotalFavorite(int totalFavorite, Pageable pageable);
    Page<Restaurant> findAllByOrderByTotalFavoriteDesc(Pageable pageable);

    //    Page<Restaurant> findByStreetAddressAndRestaurantName(Pageable pageable, String keyword);
//    Page<Restaurant> findByStreetAddressOrRestaurantName(Pageable pageable, String keyword);
    Page<Restaurant> findByStreetAddress(Pageable pageable, String keyword);

    Page<Restaurant> findByRestaurantName(Pageable pageable, String keyword);

    @Query("SELECT r FROM Restaurant r WHERE r.streetAddress LIKE %:keyword% OR r.restaurantName LIKE %:keyword%")
    Page<Restaurant> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);



}
