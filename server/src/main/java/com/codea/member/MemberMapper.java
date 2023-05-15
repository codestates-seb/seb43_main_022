package com.codea.member;

import com.codea.member.MemberDto;
import com.codea.member.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {

//    @Mapping(target = "address.streetAddress", source = "streetAddress")
//    @Mapping(target = "address.latitude", source = "latitude")
//    @Mapping(target = "address.longitude", source = "longitude")
    Member memberPostDtoToMember(MemberDto.Post memberPostDto);
    Member memberPatchDtoToMember(MemberDto.Patch memberPatchDto);
    MemberDto.Response memberToMemberResponseDto(Member member);
    List<MemberDto.Response> membersToMemberResponseDto(List<Member> members);

}
