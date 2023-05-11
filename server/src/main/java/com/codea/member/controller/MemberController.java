package com.codea.member.controller;


import com.codea.dto.MultiResponseDto;
import com.codea.member.dto.MemberDto;
import com.codea.member.entity.Member;
import com.codea.member.mapper.MemberMapper;
import com.codea.member.service.MemberService;
import com.codea.util.UriCreator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/members")
@Validated
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    @PostMapping("/signup")
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody) {
        Member member = memberMapper.memberPostDtoToMember(requestBody);
        Member createMember = memberService.createMember(member);
        MemberDto.Response responseDto = memberMapper.memberToMemberResponseDto(createMember);

        URI location = UriCreator.createUri("/members", createMember.getMemberId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return new ResponseEntity(responseDto, headers, HttpStatus.CREATED);

    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Positive long memberId,
                                      @Valid @RequestBody MemberDto.Patch requestBody,
                                      @RequestHeader("Authorization") String token) {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@"+ token);
        memberService.sameMemberTest(memberId, token);
        requestBody.setMemberId(memberId);
        Member updateMember = memberService.updateMember(memberMapper.memberPatchDtoToMember(requestBody));
        MemberDto.Response responseDto = memberMapper.memberToMemberResponseDto(updateMember);

        return new ResponseEntity(responseDto, HttpStatus.OK);
    }


    @GetMapping("/{member-id}")
    public ResponseEntity getMember(
            @PathVariable("member-id") @Positive long memberId) {
        Member member = memberService.findMember(memberId);
        MemberDto.Response responseDto = memberMapper.memberToMemberResponseDto(member);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size) {
        Page<Member> pageMembers = memberService.findMembers(page - 1, size);
        List<Member> members = pageMembers.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(memberMapper.membersToMemberResponseDto(members),
                        pageMembers),
                HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(
            @PathVariable("member-id") @Positive long memberId, @RequestHeader("Authorization") String token) {
        memberService.sameMemberTest(memberId, token);

        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PatchMapping("/{member-id}/profile-image") // image 설정 이후
//    public ResponseEntity updatephoto(
//            @PathVariable("member-id") @Positive long memberId,
//            @RequestBody MemberDto.photo requestBody,
//            @RequestHeader("Authorization") String token) {
//        memberService.sameMemberTest(memberId, token); // 변경하려는 회원이 맞는지 확인
//
//        Member member = memberService.findMember(memberId);
//        member.setphoto(requestBody.getphoto());
//        Member updatedMember = memberService.updateMember(member);
//        MemberJoinResponseDto responseDto = memberMapper.memberToMemberResponse(updatedMember);
//        return new ResponseEntity(responseDto, HttpStatus.OK);
//    }

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> handleOptionsRequest() {
        return ResponseEntity.ok().build();
    }

}