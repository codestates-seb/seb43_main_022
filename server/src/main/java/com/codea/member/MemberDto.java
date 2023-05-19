package com.codea.member;


import com.codea.address.Address;
import com.codea.favorite.FavoriteDto;
import com.codea.review.ReviewDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
//        private String email;
        private String password; // 비번 변경 요청시 비밀번호 입력하도록 나중에 기능 추가
        private String streetAddress;
        private double latitude;
        private double longitude;
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
        private boolean businessAccount;
        private List<ReviewDto.Response> reviews;
        private List<FavoriteDto.ResponseFavorite> favorites;
        private String photo;
        private int favoriteCount;
        private Address address;
    }

    @Getter
    @AllArgsConstructor
    public static class ReviewResponse {
        private long memberId;
        private String nickName;
        private String photo;
    }

}
