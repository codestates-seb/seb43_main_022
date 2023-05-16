package com.codea.restaurant;

import com.codea.BaseEntity.BaseEntity;
import com.codea.Menu.Menu;
import com.codea.address.Address;
import com.codea.category.Category;
import com.codea.favorite.Favorite;
import com.codea.member.Member;
import com.codea.review.Review;
import com.codea.tag.Tag;
import com.codea.tag.TagRestaurant;
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
    @Column(nullable = false)
    private String tel;
    @Column(length = 50, nullable = false)
    private String open_time;
    @Column
    private String photoUrl;
    @Column
    private int total_views;
    @Column
    private int total_reviews;
    @Column
    private int totalFavorite;
    @Column
    private double rating;
    @Column
    private String streetAddress;
    @Column
    private String detailAddress;
    @Column
    private double latitude;
    @Column
    private double longitude;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;


    public void incrementFavoriteCount() {
        this.totalFavorite += 1;
    }

    public void decrementFavoriteCount() {
        this.totalFavorite -= 1;
    }

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    private List<Menu> menu = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant")
    private List<TagRestaurant> tagRestaurants = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "restaurant")
    private Category category;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    private List<Favorite> favorites = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;


    public Restaurant(String name, String content, String tel, String open_time, String photoUrl, String detailAddress,
                      Category category) {
        this.name = name;
        this.content = content;
        this.tel = tel;
        this.open_time = open_time;
        this.photoUrl = photoUrl;
        this.detailAddress = detailAddress;
        this.category = category;
    }

    public void setTagRestaurant(TagRestaurant tagRestaurant) {
        this.tagRestaurants.add(tagRestaurant);
        if (tagRestaurant.getRestaurant() != this) {
            tagRestaurant.setRestaurant(this);
        }
    }

}
