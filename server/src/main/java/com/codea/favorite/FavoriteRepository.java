package com.codea.favorite;

import com.codea.member.Member;
import com.codea.restaurant.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findByMember(Member member);

    Page<Favorite> findByMember_MemberId(Long memberId, Pageable pageable);

    int countByRestaurant_RestaurantId(long restaurantId);


}