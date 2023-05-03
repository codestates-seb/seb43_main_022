package com.codea.review;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReviewMapper {
    Review reviewPostDtoToReview(ReviewDto.Post requestBody);
    Review reviewPatchDtoToReview(ReviewDto.Patch requestBody);
    ReviewDto.Response reviewToReviewResponseDto(Review review);
    List<ReviewDto.Response> reviewToReviewResponseDto(List<Review> reviews);
}
