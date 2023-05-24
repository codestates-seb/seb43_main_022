package com.codea.review;

import com.codea.Image.ImageService;
import com.codea.Image.Image;
import com.codea.Image.ImageDto;
import com.codea.Image.ImageRepository;
import com.codea.Menu.Menu;
import com.codea.Menu.MenuDto;
import com.codea.common.exception.ExceptionCode;
import com.codea.common.exception.BusinessLogicException;
import com.codea.member.Member;
import com.codea.member.MemberRepository;
import com.codea.restaurant.Restaurant;
import com.codea.restaurant.RestaurantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.codea.review.Review.ReviewStatus.REVIEW_VALID;

@Service
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;
    private final ImageRepository imageRepository;
    private final ImageService imageService;
    private final ReviewMapper reviewMapper;

    public ReviewService(ReviewRepository reviewRepository, RestaurantRepository restaurantRepository, MemberRepository memberRepository, ImageRepository imageRepository, ImageService imageService, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
        this.memberRepository = memberRepository;
        this.imageRepository = imageRepository;
        this.imageService = imageService;
        this.reviewMapper = reviewMapper;
    }

    @Transactional
    public Review createReview(long restaurantId, String email, ReviewDto.Post post) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.RESTAURANT_NOT_FOUND));
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        Review review = new Review(post.getTitle(), post.getContent(), post.getRating());
        review.setRestaurant(restaurant);
        review.setMember(member);

        //평점 구하기
        int totalScore = 0;
        int reviewCount = reviewRepository.countByRestaurant_RestaurantId(restaurantId) + 1;

        List<Review> reviewList = restaurant.getReviews();
        for (Review reviewTemp : reviewList) {
            int score = reviewTemp.getRating().getScore();
            totalScore += score;
        }

        double rating = Math.round((double) (totalScore + (review.getRating().getScore())) / (double) (reviewCount) * 10) / 10.0;
        restaurant.setRating(rating);
        restaurant.setTotal_reviews(reviewCount);

        for (ImageDto.Post imageTemp : post.getImage()) {  //평점 구하는 코드와 위치를 바꾸면 transient 인스턴스에 대한 참조 에러 발생
            String imageUrl = imageService.uploadImage(imageTemp.getImageName(), imageTemp.getImage(), email);

            Image image = new Image(imageTemp.getImageName(), imageUrl, review);
            imageRepository.save(image);
        }

        return reviewRepository.save(review);
    }

    @Transactional
    public Review updateReview(long reviewId, String email, ReviewDto.Patch patch) {
        Review findReview = findReview(reviewId);
        Review review = new Review(patch.getTitle(), patch.getContent(), patch.getRating());

        if (!findReview.getMember().getEmail().equals(email)) throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_EDIT);

        Optional.ofNullable(review.getTitle()).ifPresent(title -> findReview.setTitle(title));
        Optional.ofNullable(review.getContent()).ifPresent(content -> findReview.setContent(content));
        Optional.ofNullable(review.getRating()).ifPresent(rating -> findReview.setRating(rating));
        findReview.setModifiedAt(LocalDateTime.now());

        List<Image> newImageList = new ArrayList<>();
        Optional.ofNullable(review.getImage()).ifPresent((imageList) -> {
            imageRepository.deleteAllByReview_ReviewId(reviewId);
            for (ImageDto.Post imageTemp : patch.getImage()) {

                String imageUrl = "";
                if (imageTemp.getImage().contains("@")) {
                    imageUrl = imageTemp.getImage();
                } else {
                    imageUrl = imageService.uploadImage(imageTemp.getImageName(), imageTemp.getImage() , email);
                }

                Image newImage = new Image(imageTemp.getImageName(), imageUrl, findReview);

                imageRepository.save(newImage);
                newImageList.add(newImage);
            }
            findReview.setImage(newImageList);
        });

        return reviewRepository.save(findReview);
    }

    @Transactional
    public Review findReview(long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.REVIEW_NOT_FOUND));
    }

    @Transactional
    public Page<Review> findReviews(long restaurantId, int page, int size) {
        return reviewRepository.findByRestaurant_RestaurantIdAndStatus(restaurantId, REVIEW_VALID, PageRequest.of(page, size, Sort.by("reviewId").descending()));
    }

    @Transactional
    public void deleteReview(long reviewId) {
        Review findReview = findReview(reviewId);

        Restaurant restaurant = findReview.getRestaurant();
        long restaurantId = restaurant.getRestaurantId();

        int reviewCount = reviewRepository.countByRestaurant_RestaurantId(restaurantId);

        restaurant.setTotal_reviews(reviewCount - 1);

        reviewRepository.delete(findReview);
        restaurantRepository.save(restaurant);
    }

}
