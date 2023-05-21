package com.codea.category;

import com.codea.common.response.MultiResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {
    private final static  String CATEGORY_DEFAULT_URL = "/category";
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService,CategoryMapper categoryMapper){

        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }
    // 카테고리 등록
//    @PostMapping
//    public ResponseEntity postCategory(@Valid@RequestBody CategoryDto.Post categoryDto) {
//        Category category = categoryMapper.categoryPostDtoToCategory(categoryDto);
//        Category response = categoryService.createCategory(category);
//        URI location = UriCreator.createUri(CATEGORY_DEFAULT_URL, category.getCategoryId());
//
//
//       // return new ResponseEntity<>(categoryMapper.categoryToCategoryResponseDto(response), HttpStatus.CREATED);
//        return ResponseEntity.created(location).build();
//    }
//
//    // 카테고리 수정
//    @PatchMapping("/{category-id}")
//    public ResponseEntity patchCategory(@PathVariable("category-id")@Positive long categoryId,
//                                        @Valid@RequestBody CategoryDto.Patch categoryPatchDto) {
//        categoryPatchDto.setCategoryId(categoryId);
//        Category response = categoryService.updateCategory(categoryMapper.categoryPatchDtoToCategory(categoryPatchDto));
//        return new ResponseEntity<>(categoryMapper.categoryToCategoryResponseDto(response), HttpStatus.OK);
//    }
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
        List<Category> response = categories.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(categoryMapper.categoriesToCategoryResponseDto(response), categories), HttpStatus.OK);
    }
//    // 카테고리 삭제
//    @DeleteMapping("/{category-id}")
//    public ResponseEntity deleteCategory(@PathVariable("category-id") @Positive long categoryId){
//        categoryService.deleteCategory(categoryId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
  }

