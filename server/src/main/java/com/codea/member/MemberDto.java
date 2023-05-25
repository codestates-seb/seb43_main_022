package com.codea.member;


import com.codea.address.Address;
import com.codea.favorite.FavoriteDto;
import com.codea.review.ReviewDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
        @NotBlank
        private String nickName;
        private Boolean businessAccount;
        @NotBlank
        private String streetAddress;
        @NotNull
        private double latitude;
        @NotNull
        private double longitude;
        private String base64Image;
        private String imageName;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long memberId;
        private String nickName;
        private String password;
        private String streetAddress;
        private double latitude;
        private double longitude;

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
        private List<ReviewDto.MyPageResponse> reviews;
        private List<FavoriteDto.ResponseFavorite> favorites;
        private String image;
        private int favoriteCount;
        private Address address;
    }

    @Getter
    @AllArgsConstructor
    public static class ReviewResponse {
        private long memberId;
        private String nickName;
        private String image;
    }

    @Getter
    @AllArgsConstructor
    public static class RestaurantResponse {
        private long memberId;
        private String email;
    }

}
