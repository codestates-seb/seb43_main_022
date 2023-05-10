package com.codea.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class MemberResponseDto {
    private Long memberId;
    private String memberNickName;
    private String email;
    private String password;
    private String location;
}
