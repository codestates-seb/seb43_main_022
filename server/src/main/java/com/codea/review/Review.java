package com.codea.review;

import com.codea.Image.Image;
import com.codea.common.BaseEntity.BaseEntity;
import com.codea.member.Member;
import com.codea.restaurant.Restaurant;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE)//, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Image> image = new ArrayList<>();

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

    public Review(String title, String content, Rating rating) {
        this.title = title;
        this.content = content;
        this.rating = rating;
    }
}
