package com.codea.member.mapper;

import com.codea.member.dto.MemberPatchDto;
import com.codea.member.dto.MemberPostDto;
import com.codea.member.dto.MemberResponseDto;
import com.codea.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    MemberResponseDto memberToMemberResponseDto(Member member);
    List<MemberResponseDto> memberToMemberResponseDto(List<Member> members);

}
