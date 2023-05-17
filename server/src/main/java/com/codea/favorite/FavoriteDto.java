package com.codea.favorite;


import lombok.AllArgsConstructor;
import lombok.Getter;

public class FavoriteDto {
    @Getter
    @AllArgsConstructor
    public static class AddFavoriteRequest {
        private Long restaurantId;
        private Long memberId;
    }


    @Getter
    @AllArgsConstructor
    public static class RemoveFavoriteRequest {
        private Long restaurantId;
        private Long memberId;
    }

    @Getter
    public static class ResponseFavorite {
        private Long favoriteId;
        private String restaurantName;
        private Long memberId;
        private int favoriteCount;

        public ResponseFavorite(Favorite favorite, int favoriteCount) {
            this.favoriteId = favorite.getFavoriteId();
            this.restaurantName = favorite.getRestaurant().getRestaurant_name();
            this.memberId = favorite.getMember().getMemberId();
            this.favoriteCount = favoriteCount;
        }

    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private Long favoriteId;
        private Long RestaurantId;

        private String restaurantName;

    }
}
