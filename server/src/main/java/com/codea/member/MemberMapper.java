package com.codea.member;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberDto.Post memberPostDto);
    Member memberPatchDtoToMember(MemberDto.Patch memberPatchDto);
    MemberDto.Response memberToMemberResponseDto(Member member);
    List<MemberDto.Response> memberToMemberResponseDto(List<Member> members);

}
