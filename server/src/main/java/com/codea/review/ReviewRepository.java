package com.codea.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByRestaurant_RestaurantIdAndStatus(long restaurantId, Review.ReviewStatus status, Pageable pageable);
}
