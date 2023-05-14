package com.codea.restaurant;

import com.codea.BaseEntity.BaseEntity;
import com.codea.Menu.Menu;
import com.codea.address.Address;
import com.codea.favorite.Favorite;
import com.codea.member.Member;
import com.codea.review.Review;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Restaurant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long restaurantId;
    @Column(length = 30, nullable = false)
    private String name;
    @Column(length = 255, nullable = false)
    private String content;
    @NotBlank
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

//    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE)
//    private List<Menu> menu = new ArrayList<>();

//    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE)
//    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    private List<Favorite> favorites = new ArrayList<>();

}
