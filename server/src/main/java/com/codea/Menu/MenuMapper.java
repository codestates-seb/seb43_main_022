package com.codea.Menu;

import com.codea.review.Review;
import com.codea.review.ReviewDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MenuMapper {
    Menu menuPostDtoToMenu(MenuDto.Post requestBody);
    Menu menuPatchDtoToMenu(MenuDto.Patch requestBody);
    MenuDto.Response menuToMenuResponseDto(Menu menu);
    List<MenuDto.Response> menuToMenuResponseDto(List<Menu> menus);
}
