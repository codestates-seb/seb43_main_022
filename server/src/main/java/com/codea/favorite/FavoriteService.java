package com.codea.favorite;

import com.codea.member.Member;
import com.codea.restaurant.Restaurant;
import com.codea.restaurant.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FavoriteService {

    private FavoriteRepository favoriteRepository;
    private RestaurantRepository restaurantRepository;

    public FavoriteService(FavoriteRepository favoriteRepository, RestaurantRepository restaurantRepository) {
        this.favoriteRepository = favoriteRepository;
        this.restaurantRepository = restaurantRepository;
    }


    //    @Transactional
//    public void addToFavorites(Restaurant restaurant, Member member) {
//        Favorite favorite = new Favorite(restaurant, member, true);
//        favoriteRepository.save(favorite);
//    }

    @Transactional
    public void addToFavorites(Restaurant restaurant, Member member) {
        Favorite favorite = favoriteRepository.findByRestaurantAndMember(restaurant, member)
                .orElse(FavoriteFactory.createFavoriteWithNotFavorite(restaurant, member));
        favorite.setStatus(true);
        favoriteRepository.save(favorite);
        restaurant.incrementFavoriteCount(); // 즐겨찾기가 추가될 때 카운트를 증가시킵니다.
        restaurantRepository.save(restaurant); // 카운트가 업데이트된 restaurant를 저장합니다.
    }



    @Transactional
    public void removeFromFavorites(Restaurant restaurant, Member member) {
        favoriteRepository.deleteByRestaurantAndMember(restaurant, member);
    }

//    @Transactional
//    public void removeFavorite(Long memberId, Long restaurantId) {
//        // ...
//        favoriteRepository.findByMemberIdAndRestaurantId(memberId, restaurantId)
//                .ifPresent(favorite -> {
//                    if (favorite.isStatus()) {
//                        favorite.setStatus(false);
//                        favoriteRepository.save(favorite);
//                        restaurant.decrementFavoriteCount(); // 즐겨찾기 횟수 감소
//                        restaurantRepository.save(restaurant);
//                    }
//                });
//    }

    public List<Favorite> getFavoritesByMember(Member member) {
        return favoriteRepository.findByMember(member);
    }

    // 추가적인 즐겨찾기 관련 메서드

}
