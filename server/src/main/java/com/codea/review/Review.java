package com.codea.review;

import com.codea.BaseEntity.BaseEntity;
import com.codea.member.Member;
import com.codea.restaurant.Restaurant;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewId;
    @Column(length = 30, nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column
    private byte[] photo;
    @Enumerated(EnumType.STRING)
    private Rating rating;
    @Enumerated(EnumType.STRING)
    private ReviewStatus status = ReviewStatus.REVIEW_VALID;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    public enum Rating {
        GOOD("맛있어요"),
        NOT_GOOD("별로에요");
        @Getter
        private String rating;
        Rating(String rating) { this.rating = rating; }
    }

    public enum ReviewStatus {
        REVIEW_VALID("등록됨"),
        REVIEW_DELETED("삭제됨");
        @Getter
        private String reviewStatus;
        ReviewStatus(String status) { this.reviewStatus = status; }
    }



}
