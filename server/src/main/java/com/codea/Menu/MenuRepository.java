package com.codea.Menu;

import com.codea.member.Member;
import com.codea.review.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Page<Menu> findByRestaurant_RestaurantId(long restaurantId, Pageable pageable);
    void deleteAllByRestaurant_RestaurantId(Long restaurantId);

}
