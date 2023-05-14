package com.codea.member;


import com.codea.BaseEntity.BaseEntity;
import com.codea.favorite.Favorite;
import com.codea.restaurant.Restaurant;
import com.codea.review.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String nickName;
    @Column(nullable = false, updatable = false, unique = true)
    private String email;
    private String password;
    private String location;
    private String photo;
    @Column
    private Boolean businessAccount = false;
    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;


    public Member(Long memberId, String nickName, String email, String location, String photo, MemberStatus memberStatus) {
        this.memberId = memberId;
        this.nickName = nickName;
        this.email = email;
        this.location = location;
        this.photo = photo;
        this.memberStatus = memberStatus;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Restaurant> restaurants = new ArrayList<>();

//    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
//    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Favorite> favorites = new ArrayList<>();


    public enum MemberStatus {
        MEMBER_ACTIVE("활동중"),
        MEMBER_SLEEP("휴면 상태"),
        MEMBER_QUIT("탈퇴");

        @Getter
        private String status;

        MemberStatus(String status) {
            this.status = status;
        }
    }

}