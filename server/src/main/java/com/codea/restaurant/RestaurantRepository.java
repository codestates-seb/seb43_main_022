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

    @Query("SELECT DISTINCT r FROM Restaurant r LEFT JOIN r.tagRestaurants tr LEFT JOIN tr.tag t " +
            "WHERE r.address.streetAddress LIKE %:keyword% OR r.restaurantName LIKE %:keyword% OR t.name LIKE %:keyword% OR r.category.name LIKE %:keyword%")
    List<Restaurant> searchByKeyword(@Param("keyword") String keyword);
    Page<Restaurant> findByCategory_Name(String name, Pageable pageable);


}
