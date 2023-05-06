package com.codea.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;


@Getter
@AllArgsConstructor
public class MemberPatchDto {
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

