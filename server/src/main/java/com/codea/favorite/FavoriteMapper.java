package com.codea.favorite;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FavoriteMapper {
    FavoriteDto.ResponseFavorite favoriteToFavoriteResponseDto(Favorite favorite);
    List<FavoriteDto.ResponseFavorite> favoritesToFavoriteResponseDto(List<Favorite> favorites);

}
