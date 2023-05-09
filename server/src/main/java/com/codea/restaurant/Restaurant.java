package com.codea.restaurant;

import com.codea.BaseEntity.BaseEntity;
import com.codea.member.Member;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Restaurant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long restaurantId;
    @Column(length = 30, nullable = false)
    private String name;
    @Column(length = 255, nullable = false)
    private String content;
    @Column(length = 50, nullable = false)
    private String location;
    @Column(nullable = false)
    private String tel;
    @Column(length = 50, nullable = false)
    private String open_time;
    @Column
    private String photo;
    @Column
    private int total_views;
    @Column
    private int total_reviews;
    @Column
    private int total_favorite;
    @Column
    private double rating;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

}
