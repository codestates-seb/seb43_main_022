package com.codea.member;


import com.codea.favorite.FavoriteDto;
import com.codea.review.ReviewDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;


public class MemberDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Post {
        @NotBlank
        @Email(message = "올바른 이메일 형태가 아닙니다.")
        private String email;
        @NotBlank(message = "비밀번호는 공백이 아니어야 합니다.")
        private String password;
        private String nickName;
        private String photo;
        private String location;
        private Boolean businessAccount;
        private String streetAddress;
        private double latitude;
        private double longitude;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long memberId;
        private String nickName;
        private String email;
        private String password; // 비번 변경 요청시 비밀번호 입력하도록 나중에 기능 추가
        private String location;
        private String photo;

        public void setMemberId(long memberId) {
            this.memberId = memberId;
        }
    }


    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {
        private long memberId;
        private String nickName;
        private String email;
        private String location;
        private boolean businessAccount;
        private String photo;
        private List<ReviewDto.Response> reviews;
        private int favoriteCount;
        private List<FavoriteDto.Response> favorites;
        private Address address;



    }

    @Getter
    @AllArgsConstructor
    public static class ReviewResponse {
        private long memberId;
        private String nickName;
        private String photo;
    }

//
//    private long restaurantId;
//    @Column(length = 30, nullable = false)
//    private String name;
//    @Column(length = 255, nullable = false)
//    private String content;
//    @NotBlank
//    private String location;
//    @Column(nullable = false)
//    private String tel;
//    @Column(length = 50, nullable = false)
//    private String open_time;
//    @Column
//    private String photo;
//    @Column

}
