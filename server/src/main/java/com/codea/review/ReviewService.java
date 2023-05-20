package com.codea.review;

import com.codea.exception.ExceptionCode;
import com.codea.exception.BusinessLogicException;
import com.codea.member.Member;
import com.codea.member.MemberRepository;
import com.codea.restaurant.Restaurant;
import com.codea.restaurant.RestaurantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.codea.review.Review.ReviewStatus.REVIEW_VALID;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;

    public ReviewService(ReviewRepository reviewRepository, RestaurantRepository restaurantRepository, MemberRepository memberRepository) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Review createReview(long restaurantId, String email, Review review) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.RESTAURANT_NOT_FOUND));
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        review.setRestaurant(restaurant);
        review.setMember(member);

        int totalScore = 0;

        List<Review> reviewList = restaurant.getReviews(); // 리뷰를 리뷰의 수가 아니라.
        for (Review reviewTemp : reviewList) {
            int score = review.getRating().getScore();
            totalScore += score;
        }

        double rating = (double) totalScore / (restaurant.getTotal_reviews() + 1);
        restaurant.setRating(rating);

//        int rating = review.getRating().getScore(); //Like("맛있어요", 5), Hate
//        total = rating + total;  //총 리뷰점수 구해서
//        double average = total / (restaurant.getTotal_reviews() + 1);      //나눠서
//        restaurant.setRating(average);                // 다시 반영
//
//int a = restaurant.getReviews().size();
//
//for(Review reviewScore : restaurant.getReviews()){
//    total = reviewScore.getRating().getScore();
//}

        return reviewRepository.save(review);
    }

    @Transactional
    public Review updateReview(long reviewId, String email, Review review) {
        Review findReview = findReview(reviewId);

        if (!findReview.getMember().getEmail().equals(email)) throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_EDIT);

        Optional.ofNullable(review.getTitle()).ifPresent(title -> findReview.setTitle(title));
        Optional.ofNullable(review.getContent()).ifPresent(content -> findReview.setContent(content));
        Optional.ofNullable(review.getPhoto()).ifPresent(photo -> findReview.setPhoto(photo));
        Optional.ofNullable(review.getRating()).ifPresent(rating -> findReview.setRating(rating));
        findReview.setModifiedAt(LocalDateTime.now());

        return reviewRepository.save(findReview);
    }

    public Review findReview(long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.REVIEW_NOT_FOUND));
    }

    public Page<Review> findReviews(long restaurantId, int page, int size) {
        return reviewRepository.findByRestaurant_RestaurantIdAndStatus(restaurantId, REVIEW_VALID, PageRequest.of(page, size, Sort.by("reviewId").descending()));
    }

    public void deleteReview(long reviewId) {
        Review findReview = findReview(reviewId);

//        findReview.setStatus(Review.ReviewStatus.REVIEW_DELETED);
        reviewRepository.delete(findReview);
    }
    public double getAverageRatingForRestaurant(long restaurantId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        if (restaurant != null){
            return  restaurant.getAverageRating();
        }
        return 0;
    }
}
