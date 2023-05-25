package com.codea.restaurant;

import com.codea.common.BaseEntity.BaseEntity;
import com.codea.Menu.Menu;
import com.codea.address.Address;
import com.codea.category.Category;
import com.codea.favorite.Favorite;
import com.codea.member.Member;
import com.codea.review.Review;
import com.codea.tag.TagRestaurant;
import lombok.*;

import javax.persistence.*;
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
    private String restaurantName;
    @Column(nullable = false)
    private String content;
    @Column(length = 30, nullable = false)
    private String tel;
    @Column(length = 50, nullable = false)
    private String open_time;
    @Column(nullable = false)
    private String image;
    @Column
    private int total_views;
    @Column
    private int total_reviews;
    @Column
    private int totalFavorite;
    @Column(nullable = false)
    private double rating;
//    @Column
//    private String streetAddress;
    @Column(nullable = false)
    private String detailAddress;
//    @Column
//    private double latitude;
//    @Column
//    private double longitude;


    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
//    private double averageRating;

    @OneToMany(mappedBy = "restaurant", cascade =CascadeType.REMOVE)//, cascade = {CascadeType.PERSIST, CascadeType.MERGE})//, cascade = CascadeType.ALL)
    private List<Menu> menu = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade ={CascadeType.PERSIST,  CascadeType.REMOVE})//, cascade = {CascadeType.PERSIST, CascadeType.MERGE})//, cascade = CascadeType.ALL)
    private List<TagRestaurant> tagRestaurants = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = {CascadeType.PERSIST,  CascadeType.REMOVE})//, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE)//, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Favorite> favorites = new ArrayList<>();


    public Restaurant(String restaurantName, String content, String tel, String open_time, String image, String detailAddress) {
        this.restaurantName = restaurantName;
        this.content = content;
        this.tel = tel;
        this.open_time = open_time;
        this.image = image;
        this.detailAddress = detailAddress;
    }

    public void setTagRestaurant(TagRestaurant tagRestaurant) {
        this.tagRestaurants.add(tagRestaurant);
        if (tagRestaurant.getRestaurant() != this) {
            tagRestaurant.setRestaurant(this);
        }
    }

}
