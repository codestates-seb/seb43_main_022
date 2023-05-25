package com.codea.category;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto.Response categoryToCategoryResponseDto(Category category);
    List<CategoryDto.Response> categoriesToCategoryResponseDto(List<Category> category);

}
