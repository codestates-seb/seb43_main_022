package com.codea.favorite;

import com.codea.member.Member;
import com.codea.restaurant.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    void deleteByRestaurantAndMember(Restaurant restaurant, Member member);
}