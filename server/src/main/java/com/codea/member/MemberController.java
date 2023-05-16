package com.codea.member;


import com.codea.address.Address;
import com.codea.address.AddressDto;
import com.codea.address.AddressMapper;
import com.codea.dto.MultiResponseDto;
import com.codea.restaurant.Restaurant;
import com.codea.review.Review;
import com.codea.review.ReviewDto;
import com.codea.review.ReviewMapper;
import com.codea.utils.UriCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/members")
@Validated
@Slf4j
@Transactional
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final AddressMapper addressMapper;

    public MemberController(MemberService memberService, MemberMapper memberMapper, AddressMapper addressMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
        this.addressMapper = addressMapper;
    }

    @PostMapping("/signup")
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody) {
        // member 정보와 도로명주소를 받는다.
        // 도로명 주소를 통해 Address 객체를 얻는다.
        // Address 객체를 member 엔티티에 저장한다.

        // requestBody 안에 member 정보와 도로명주소, 위도, 경도가 함께 저장되어 있음

        AddressDto.Post addressDto = new AddressDto.Post(requestBody.getStreetAddress(), requestBody.getLatitude(), requestBody.getLongitude());
        Address address = addressMapper.addressPostDtoToAddress(addressDto);

        Member member = memberService.createMember(address, memberMapper.memberPostDtoToMember(requestBody));
        MemberDto.Response responseDto = memberMapper.memberToMemberResponseDto(member);

        URI location = UriCreator.createUri("/members", member.getMemberId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return new ResponseEntity(responseDto, headers, HttpStatus.CREATED);

    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Positive long memberId,
                                      @Valid @RequestBody MemberDto.Patch requestBody,
                                      @AuthenticationPrincipal String email) {
//        memberService.sameMemberTest(memberId, token);
//        requestBody.setMemberId(memberId);

        AddressDto.Post addressDto = new AddressDto.Post(requestBody.getStreetAddress(), requestBody.getLatitude(), requestBody.getLongitude());
        Address address = addressMapper.addressPostDtoToAddress(addressDto);

        Member member = memberService.updateMember(memberId, email, address, memberMapper.memberPatchDtoToMember(requestBody));


//        Member updateMember = memberService.updateMember(address, memberMapper.memberPatchDtoToMember(requestBody));
//        MemberDto.Response responseDto = memberMapper.memberToMemberResponseDto(updateMember);

        return new ResponseEntity<>(memberMapper.memberToMemberResponseDto(member), HttpStatus.OK);
    }


    @Transactional
    @GetMapping("/{member-id}")
    public ResponseEntity getMember(
            @PathVariable("member-id") @Positive long memberId) {
        Member member = memberService.findMember(memberId);
        MemberDto.Response responseDto = memberMapper.memberToMemberResponseDto(member);

        List<Review> reviews = memberService.getReviewsByMember(member);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @Transactional
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

    @DeleteMapping("/delete-account")
    public ResponseEntity deleteMember(@AuthenticationPrincipal String email) {
//        memberService.sameMemberTest(email);

        memberService.deleteMember(email);

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