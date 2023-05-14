package com.codea.favorite;

import com.codea.member.Member;
import com.codea.member.MemberDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FavoriteMapper {
    Favorite favoritePostFavoriteDtoToFavorite(FavoriteDto.PostFavorite requestBody);
//    Favorite favoritePatchDtoToFavorite(FavoriteDto.ResponseFavorite requestBody);
    FavoriteDto.ResponseFavorite favoriteToFavoriteResponseDto(Favorite favorite);
    List<FavoriteDto.ResponseFavorite> favoritesToFavoriteResponseDto(List<Favorite> favorites);

}
