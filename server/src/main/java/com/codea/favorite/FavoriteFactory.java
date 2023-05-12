package com.codea.favorite;

import com.codea.member.Member;
import com.codea.restaurant.Restaurant;

public class FavoriteFactory {
    public static Favorite createFavoriteWithNotFavorite(Restaurant restaurant, Member member) {
        return new Favorite(1L, restaurant, member, false);
    }

    public static Favorite createFavoriteWithFavorite(Restaurant restaurant, Member member) {
        return new Favorite(1L, restaurant, member, true);
    }
}