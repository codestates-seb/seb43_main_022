package com.codea.favorite;


import com.codea.address.Address;
import com.codea.category.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class FavoriteDto {

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
            this.streetAddress = favorite.getRestaurant().getAddress().getStreetAddress();
        }

    }

}
