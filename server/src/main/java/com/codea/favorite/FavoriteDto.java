package com.codea.favorite;


import com.codea.category.Category;
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
        private Long restaurantId;
        private String category;
        private String streetAddress;

        public ResponseFavorite(Favorite favorite, int favoriteCount) {
            this.favoriteId = favorite.getFavoriteId();
            this.restaurantName = favorite.getRestaurant().getRestaurantName();
            this.memberId = favorite.getMember().getMemberId();
            this.favoriteCount = favoriteCount;
            this.restaurantId = favorite.getRestaurant().getRestaurantId();
            this.category = favorite.getRestaurant().getCategory().getName();
//            this.streetAddress = favorite.getRestaurant();
        }

    }

//    @Getter
//    @AllArgsConstructor
//    public static class Response {
//        private Long favoriteId;
//        private Long RestaurantId;
//
//        private String restaurantName;
//
//    }
}
