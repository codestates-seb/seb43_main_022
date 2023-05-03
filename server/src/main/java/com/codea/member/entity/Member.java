package com.codea.member.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String memberNickName;
    private String email;
    private String password;
    private String location;
    private String profileImage;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public Member(Long memberId, String memberNickName, String email, String location, String profileImage,
                  LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.memberId = memberId;
        this.memberNickName = memberNickName;
        this.email = email;
        this.location = location;
        this.profileImage = profileImage;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}