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
}
