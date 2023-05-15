package com.codea.member;


import com.codea.address.Address;
import com.codea.address.AddressRepository;
import com.codea.auth.utils.CustomAuthorityUtils;
import com.codea.exception.BusinessLogicException;
import com.codea.exception.ExceptionCode;
import com.codea.favorite.FavoriteRepository;
import com.codea.review.Review;
import com.codea.review.ReviewRepository;
import com.codea.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
//    private final CustomAuthorityUtils authorityUtils;
    private final JwtUtil jwtUtil;
    private final ReviewRepository reviewRepository;
    private final AddressRepository addressRepository;

    public Member createMember(Member member) {
        verifyExistsEmail(member.getEmail());

        String encryptedPassword = passwordEncoder.encode(member.getPassword()); // Password 단방향 암호화
        member.setPassword(encryptedPassword);

        if (member.getBusinessAccount()) {
            member.getRoles().add("ROLE_BUSINESS");
        } else {
            member.getRoles().add("ROLE_MEMBER");
        }

//        List<String> roles = authorityUtils.createRoles(member.getEmail()); // admin 권한 설정
//        member.setRoles(roles);

//        if (member.getPhoto() == null || member.getPhoto().isEmpty()) { // 기본 이미지 등록
//            member.setPhoto("https://velog.velcdn.com/images/persestitan/post/5ef6f63a-c279-465d-b65d-97ff39848f6c/image.jpeg");
//        }

        Address savedAddress = addressRepository.save(address);
        member.setAddress(savedAddress);

        Member savedMember = memberRepository.save(member);

        return savedMember;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Member updateMember(Member member) {
        Member findMember = findVerifiedMember(member.getMemberId());

        Optional.ofNullable(member.getNickName())
                .ifPresent(name -> findMember.setNickName(name));
        Optional.ofNullable(member.getPassword())
                .ifPresent(password -> findMember.setPassword(password));
        Optional.ofNullable(member.getLocation())
                .ifPresent(location -> findMember.setLocation(location));
//        Optional.ofNullable(member.getPhoto())
//                .ifPresent(image -> findMember.setPhoto(image));

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
        //탈퇴회원으로 상태변경
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

    public void sameMemberTest(long memberId, String token) {
        String email = jwtUtil.extractEmailFromToken(token);
        Member findMember = findVerifiedMember(memberId);

        if (!email.equals(findMember.getEmail())) {
            throw new BusinessLogicException(ExceptionCode.INVALID_PERMISSION, String.format("유저(%s)가 권한을 가지고 있지 않습니다. 사용자(%s) 정보를 수정할 수 없습니다.", email, findMember.getEmail()));
        }
    }

    public List<Review> getReviewsByMember(Member member) {
        return reviewRepository.findByMember(member);
    }


}
