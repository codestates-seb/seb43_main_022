package com.codea.review;

import com.codea.BaseEntity.BaseEntity;
import com.codea.member.Member;
import com.codea.restaurant.Restaurant;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private String photo;
    @Enumerated(EnumType.STRING)
    private Rating rating;
    @Enumerated(EnumType.STRING)
    private ReviewStatus status = ReviewStatus.REVIEW_VALID;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;


    public enum Rating {
        LIKE("맛있어요",5),
        HATE("별로에요",1);
        @Getter
        private String rating;
        @Getter
        private int score;
        Rating(String rating, int score) {
            this.rating = rating;
            this.score = score;}
    }

    public enum ReviewStatus {
        REVIEW_VALID("등록됨"),
        REVIEW_DELETED("삭제됨");
        @Getter
        private String reviewStatus;
        ReviewStatus(String status) { this.reviewStatus = status; }
    }

    public void setRating(Rating rating){
        this.rating = rating;
    }


}
