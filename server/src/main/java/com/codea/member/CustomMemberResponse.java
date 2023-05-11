package com.codea.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CustomMemberResponse {

    private long memberId;
    private String email;
    private String photo;
    private String memberNickName;
    private String password;
    private String location;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;


    public static CustomMemberResponse from(CustomMemberDto dto){
        return new CustomMemberResponse(
                dto.getMemberId(),
                dto.getEmail(),
                dto.getPhoto(),
                dto.getMemberNickName(),
                dto.getPassword(),
                dto.getLocation(),
                dto.getCreatedAt(),
                dto.getModifiedAt()
        );
    }
}
