package com.codea.member.mapper;

import com.codea.member.dto.MemberDto;
import com.codea.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    Member memberPostDtoToMember(MemberDto.Post memberPostDto);
    Member memberPatchDtoToMember(MemberDto.Patch memberPatchDto);
    MemberDto.Response memberToMemberResponseDto(Member member);
    List<MemberDto.Response> membersToMemberResponseDto(List<Member> members);

}
