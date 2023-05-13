package com.codea.favorite;

import com.codea.member.Member;
import com.codea.restaurant.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

    private FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public void addToFavorites(Restaurant restaurant, Member member) {
        Favorite favorite = new Favorite(restaurant, member, true);
        favoriteRepository.save(favorite);
    }

    public void removeFromFavorites(Restaurant restaurant, Member member) {
        favoriteRepository.deleteByRestaurantAndMember(restaurant, member);
    }

    // 추가적인 즐겨찾기 관련 메서드

}
