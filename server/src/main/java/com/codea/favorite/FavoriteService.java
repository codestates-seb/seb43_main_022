package com.codea.favorite;

import com.codea.member.Member;
import com.codea.restaurant.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FavoriteService {

    private FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @Transactional
    public void addToFavorites(Restaurant restaurant, Member member) {
        Favorite favorite = new Favorite(restaurant, member, true);
        favoriteRepository.save(favorite);
    }

    @Transactional
    public void removeFromFavorites(Restaurant restaurant, Member member) {
        favoriteRepository.deleteByRestaurantAndMember(restaurant, member);
    }

    public List<Favorite> getFavoritesByMember(Member member) {
        return favoriteRepository.findByMember(member);
    }

    // 추가적인 즐겨찾기 관련 메서드

}
