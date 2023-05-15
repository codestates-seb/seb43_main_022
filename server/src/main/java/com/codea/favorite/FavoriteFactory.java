package com.codea.favorite;

import com.codea.member.Member;
import com.codea.restaurant.Restaurant;

public class FavoriteFactory {
    public static Favorite createFavoriteWithNotFavorite(Restaurant restaurant, Member member) {
        return new Favorite(restaurant, member, false);
    }

    public static Favorite createFavoriteWithFavorite(Long favoriteId, Restaurant restaurant, Member member) {
        Favorite favorite = new Favorite(restaurant, member, true);
        favorite.setFavoriteId(favoriteId);
        return favorite;
    }
}