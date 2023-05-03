package com.codea.member.service;


import com.codea.excption.BusinessLogicException;
import com.codea.excption.ExceptionCode;
import com.codea.member.entity.Member;
import com.codea.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@AllArgsConstructor
@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;
//    private final PasswordEncoder passwordEncoder;


    public Member createMember(Member member) {
        verifyExistsEmail(member.getEmail());

//        String encryptedPassword = passwordEncoder.encode(member.getPassword()); // Password 단방향 암호화
//        member.setPassword(encryptedPassword);


//        List<String> roles = authorityUtils.createRoles(member.getEmail()); // 권한 설정
//        member.setRoles(roles);
//
//        if (member.getProfileImage() == null || member.getProfileImage().isEmpty()) { // 기본 이미지 등록
//            member.setProfileImage("https://velog.velcdn.com/images/persestitan/post/5ef6f63a-c279-465d-b65d-97ff39848f6c/image.jpeg");
//        }

        return memberRepository.save(member);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Member updateMember(Member member) {
        Member findMember = findVerifiedMember(member.getMemberId());

        Optional.ofNullable(member.getMemberNickName())
                .ifPresent(name -> findMember.setMemberNickName(name));
        Optional.ofNullable(member.getPassword())
                .ifPresent(password -> findMember.setPassword(password));
        Optional.ofNullable(member.getLocation())
                .ifPresent(location -> findMember.setLocation(location));
//        Optional.ofNullable(member.getProfileImage())
//                .ifPresent(image -> findMember.setProfileImage(image));

        return memberRepository.save(findMember);
    }

    @Transactional(readOnly = true)
    public Member findMember(long memberId) {
        return findVerifiedMember(memberId);
    }

    public Page<Member> findMembers(int page, int size) {
        return memberRepository.findAll(PageRequest.of(page, size,
                Sort.by("memberId").descending()));
    }


    public void deleteMember(long memberId) {
        Member findMember = findVerifiedMember(memberId);

        memberRepository.delete(findMember);
    }

    @Transactional(readOnly = true)
    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember =
                memberRepository.findById(memberId);
        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS, String.format("%s는 이미 가입한 이메일입니다.", email));
    }



}
