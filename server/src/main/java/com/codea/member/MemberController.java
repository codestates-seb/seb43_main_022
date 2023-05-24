package com.codea.member;


import com.codea.Image.ImageService;
import com.codea.address.Address;
import com.codea.address.AddressDto;
import com.codea.address.AddressMapper;
import com.codea.auth.userdetails.MemberDetailsService;
import com.codea.common.dto.MultiResponseDto;
import com.codea.common.utils.UriCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/members")
@Validated
@Slf4j
@Transactional
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final AddressMapper addressMapper;
    private final MemberDetailsService memberDetailsService;
    private final ImageService imageService;

    public MemberController(MemberService memberService, MemberMapper memberMapper, AddressMapper addressMapper, MemberDetailsService memberDetailsService, ImageService imageService) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
        this.addressMapper = addressMapper;
        this.memberDetailsService = memberDetailsService;
        this.imageService = imageService;
    }
    @Transactional
    @PostMapping("/signup")
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody) {

        AddressDto.Post addressDto = new AddressDto.Post(requestBody.getStreetAddress(), requestBody.getLatitude(), requestBody.getLongitude());
        Address address = addressMapper.addressPostDtoToAddress(addressDto);

        String imageUrl = "https://main022.s3.ap-northeast-2.amazonaws.com/image/default_profile.png";
        if (requestBody.getBase64Image() != null && requestBody.getImageName() != null && !(requestBody.getBase64Image().isEmpty()) && !(requestBody.getImageName().isEmpty())) {
            imageUrl = imageService.uploadImage(requestBody.getImageName(), requestBody.getBase64Image(), requestBody.getEmail());
        }

        Member member = memberService.createMember(address, imageUrl, memberMapper.memberPostDtoToMember(requestBody));
        MemberDto.Response responseDto = memberMapper.memberToMemberResponseDto(member);

        URI location = UriCreator.createUri("/members", member.getMemberId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return new ResponseEntity(responseDto, headers, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity patchMember(@Valid @RequestBody MemberDto.Patch requestBody,
                                      @AuthenticationPrincipal String email) {

        AddressDto.Post addressDto = new AddressDto.Post(requestBody.getStreetAddress(), requestBody.getLatitude(), requestBody.getLongitude());
        Address address = addressMapper.addressPostDtoToAddress(addressDto);

        Member member = memberService.updateMember(email, address, memberMapper.memberPatchDtoToMember(requestBody));


        return new ResponseEntity<>(memberMapper.memberToMemberResponseDto(member), HttpStatus.OK);
    }


    @Transactional
    @GetMapping("/mypage")
    public ResponseEntity getMember(@AuthenticationPrincipal String email) {
        Member member = memberService.findMember(email);
        MemberDto.Response responseDto = memberMapper.memberToMemberResponseDto(member);

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

    @DeleteMapping
    public ResponseEntity deleteMember(@AuthenticationPrincipal String email) {

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


    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        memberDetailsService.logout(request, response);
    }
}