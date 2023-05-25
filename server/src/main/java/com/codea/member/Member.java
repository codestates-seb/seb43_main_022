package com.codea.member;


import com.codea.common.BaseEntity.BaseEntity;
import com.codea.address.Address;
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
    @Column(length = 30, nullable = false)
    private String nickName;
    @Column(length = 30, nullable = false, updatable = false, unique = true)
    private String email;
    @Column(length = 68, nullable = false)
    private String password;
    @Column(nullable = false)
    private  String image;
    @Column
    private Boolean businessAccount = false;
    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(length = 20, nullable = false)
    private List<String> roles = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)//, cascade = CascadeType.REMOVE)
    private List<Restaurant> restaurants = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)//,  cascade = CascadeType.REMOVE)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)//, cascade = CascadeType.REMOVE)
    private List<Favorite> favorites = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;


    public Member(Long memberId, String nickName, String email, MemberStatus memberStatus) {
        this.memberId = memberId;
        this.nickName = nickName;
        this.email = email;
        this.memberStatus = memberStatus;
    }

    @Getter
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