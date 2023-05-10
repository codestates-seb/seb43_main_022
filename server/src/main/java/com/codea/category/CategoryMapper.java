package com.codea.category;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category categoryPostDtoToCategory(CategoryDto.Post categoryPostDto);
    Category categoryPatchDtoToCategory(CategoryDto.Patch categoryPatchDto);
    CategoryDto.Response categoryToCategoryResponseDto(Category category);

}
