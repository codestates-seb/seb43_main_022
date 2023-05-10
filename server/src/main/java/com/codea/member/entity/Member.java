package com.codea.member.entity;


import com.codea.BaseEntity.BaseEntity;
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
    private String memberNickName;
    @Column(nullable = false, updatable = false, unique = true)
    private String email;
    private String password;
    private String location;
    private String profileImage;
    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

    public Member(Long memberId, String memberNickName, String email, String location, String profileImage) {
        this.memberId = memberId;
        this.memberNickName = memberNickName;
        this.email = email;
        this.location = location;
        this.profileImage = profileImage;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();










//    @OneToMany(mappedBy = "member")
//    private List<Restaurant> restaurants = new ArrayList<>();
//    @OneToMany(mappedBy = "member")
//    private List<Review> reviews = new ArrayList<>();
//


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