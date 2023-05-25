package com.codea.review;

import com.codea.member.Member;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByRestaurant_RestaurantIdAndStatus(long restaurantId, Review.ReviewStatus status, Pageable pageable);

    int countByRestaurant_RestaurantId(long restaurantId);

}
