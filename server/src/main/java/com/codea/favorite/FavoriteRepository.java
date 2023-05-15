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
//
//    void deleteByRestaurantAndMember(Restaurant restaurant, Member member);
//
//    Optional<Favorite> findByRestaurantAndMember(Restaurant restaurant, Member member);
//    Optional<Favorite> findByMember_MemberIdAndRestaurant_RestaurantId(Restaurant restaurant, Member member);
//    Optional<Favorite> findByEmail(String email);

    Page<Favorite> findByMember_MemberIdAndStatus(Long memberId, Boolean status, Pageable pageable);

    int countByRestaurant_RestaurantId(long restaurantId);


}