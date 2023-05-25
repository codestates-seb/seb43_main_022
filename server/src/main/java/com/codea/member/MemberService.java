package com.codea.member;


import com.amazonaws.services.s3.AmazonS3;
import com.codea.address.Address;
import com.codea.address.AddressRepository;
import com.codea.common.exception.BusinessLogicException;
import com.codea.common.exception.ExceptionCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Optional;



@Transactional
@Service
public class MemberService {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;
    private final RedisTemplate redisTemplate;
    private final AmazonS3 amazonS3;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, AddressRepository addressRepository, RedisTemplate redisTemplate, AmazonS3 amazonS3) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.addressRepository = addressRepository;
        this.redisTemplate = redisTemplate;
        this.amazonS3 = amazonS3;
    }

    public Member createMember(Address address, String imageUrl, Member member) {
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

        if (address != null) {
            String streetAddress = address.getStreetAddress();
            Address persistedAddress = addressRepository.findByStreetAddress(streetAddress)
                    .orElseGet(() -> addressRepository.save(address));
            member.setAddress(persistedAddress);
        }

        member.setImage(imageUrl);

        return memberRepository.save(member);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Member updateMember(String email, Address address, Member member) {

        Member findMember = findMember(email);

        if (!findMember.getEmail().equals(email)) throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_EDIT);

        Optional.ofNullable(member.getNickName()).ifPresent(name -> findMember.setNickName(name));
        Optional.ofNullable(member.getPassword()).ifPresent(password -> {
            String encryptedPassword = passwordEncoder.encode(password);
            findMember.setPassword(encryptedPassword);
        });

        findMember.setModifiedAt(LocalDateTime.now());

        if (address != null) {
            String streetAddress = address.getStreetAddress();
            Address persistedAddress = addressRepository.findByStreetAddress(streetAddress)
                    .orElseGet(() -> addressRepository.save(address));
            findMember.setAddress(persistedAddress);
        }

        return memberRepository.save(findMember);
    }


    @Transactional(readOnly = true)
    public Member findMember(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    public Page<Member> findMembers(int page, int size) {
        return memberRepository.findAll(PageRequest.of(page, size,
                Sort.by("memberId").descending()));
    }


    public void deleteMember(String email) {
        Member findMember = findMember(email);

        //탈퇴회원으로 상태변경
        findMember.setMemberStatus(Member.MemberStatus.MEMBER_QUIT);
        memberRepository.save(findMember);
    }


    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS, String.format("%s는 이미 가입한 이메일입니다.", email));
    }


    public void logout(HttpServletRequest request, HttpServletResponse response) {
        String refreshJws = request.getHeader("Refresh");

        redisTemplate.delete(refreshJws);
        response.setHeader("Authorization", "");
        response.setHeader("Refresh", "");
    }

}
