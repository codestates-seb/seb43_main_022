package com.codea.category;

import com.codea.common.exception.BusinessLogicException;
import com.codea.common.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

//    public Category createCategory(Category category){
//        verifyExistsName(category.getName());
//        return categoryRepository.save(category);
//    }
//    public Category updateCategory(Category category){
//       Category findCategory = findVerifiedCategory(category.getCategoryId());
//
//       Optional.ofNullable(category.getName())
//               .ifPresent(name-> findCategory.setName(name));
//
//       return categoryRepository.save(findCategory);
//    }
    public Category findCategory(long categoryId){
       return findVerifiedCategory(categoryId);
    }

    public Page<Category> findCategories(int page, int size) {
        return  categoryRepository.findAll(PageRequest.of(page, size,
                Sort.by("categoryId")));
    }

//    public void deleteCategory(long categoryId){
//        Category findCategory = findVerifiedCategory(categoryId);
//        categoryRepository.delete(findCategory);
//
//    }
    public Category findVerifiedCategory(long categoryId){
        Optional<Category> optionalCategory =
                categoryRepository.findById(categoryId);
        Category findCategory =
                optionalCategory.orElseThrow(()->
                        new BusinessLogicException(ExceptionCode.CATEGORY_NOT_FOUND));

        return findCategory;
    }
//    private void verifyExistsName(String name){
//        Optional<Category> category = categoryRepository.findByName(name);
//        if(category.isPresent())
//            throw new BusinessLogicException(ExceptionCode.CATEGORY_EXISTS);
//    }
}
