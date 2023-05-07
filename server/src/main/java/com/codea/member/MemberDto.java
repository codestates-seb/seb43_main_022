package com.codea.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class MemberDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotBlank
        @Email(message = "올바른 이메일 형태가 아닙니다.")
        private String email;
        @NotBlank(message = "비밀번호는 공백이 아니어야 합니다.")
        private String password;
        private String memberNickName;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long memberId;
        private String memberNickName;
        @NotBlank(message = "비밀번호는 공백이 아니어야 합니다.")
        private String password; // 비번 변경 요청시 비밀번호 입력하도록 나중에 기능 추가
        private String location;
        private String profileImage;

        public void setMemberId(long memberId) {
            this.memberId = memberId;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private Long memberId;
        private String memberNickName;
        private String email;
        private String password;
        private String location;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }
}
