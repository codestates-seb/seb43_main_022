package com.codea.member.dto;

import com.codea.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class CustomMemberDto {

    private long memberId;
    private String memberNickName;
    private String email;
    private String password;
    private String location;
    private String photo;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static CustomMemberDto from(Member entity) {
        return new CustomMemberDto(
                entity.getMemberId(),
                entity.getMemberNickName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getLocation(),
                entity.getPhoto(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }

}
