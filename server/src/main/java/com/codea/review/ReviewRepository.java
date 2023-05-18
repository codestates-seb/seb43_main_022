package com.codea.review;

import com.codea.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByRestaurant_RestaurantIdAndStatus(long restaurantId, Review.ReviewStatus status, Pageable pageable);

    List<Review> findByMember(Member member);




}
