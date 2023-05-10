package com.codea.category;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;
import com.codea.category.CategoryMapper;

@RestController
@RequestMapping("/categories")
@Validated
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService,CategoryMapper categoryMapper){

        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }
    // 카테고리 등록
    @PostMapping
    public ResponseEntity postCategory(@Valid@RequestBody CategoryDto.Post categoryDto) {
        Category category = categoryMapper.categoryPostDtoToCategory(categoryDto);
        Category response = categoryService.createCategory(category);



        return new ResponseEntity<>(categoryMapper.categoryToCategoryResponseDto(response), HttpStatus.CREATED);
    }

    // 카테고리 수정
    @PatchMapping("/{category-id}")
    public ResponseEntity patchCategory(@PathVariable("category-id")@Positive long categoryId,
                                        @Valid@RequestBody CategoryDto.Patch categoryPatchDto) {
        categoryPatchDto.setCategoryId(categoryId);
        Category response = categoryService.updateCategory(categoryMapper.categoryPatchDtoToCategory(categoryPatchDto));
        return new ResponseEntity<>(categoryMapper.categoryToCategoryResponseDto(response), HttpStatus.OK);
    }
    // 하나의 카테고리 조회
    @GetMapping("/{category-id}")
    public ResponseEntity getCategory(@PathVariable("category-id")@Positive long categoryId){
        Category response = categoryService.findCategory(categoryId);
        return new ResponseEntity<>(categoryMapper.categoryToCategoryResponseDto(response),HttpStatus.OK);
    }
    // 모든 카테고리 조회
    @GetMapping
    public ResponseEntity getCategories(@Positive @RequestParam int page,
                                        @Positive @RequestParam int size){
        Page<Category> categories = categoryService.findCategories(page -1, size);
        List<CategoryDto.Response> response = categories.stream().map(
                category-> categoryMapper.categoryToCategoryResponseDto(category)
        ).collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // 카테고리 삭제
    @DeleteMapping("/{category-id}")
    public ResponseEntity deleteCategory(@PathVariable("category-id") @Positive long categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
  }

