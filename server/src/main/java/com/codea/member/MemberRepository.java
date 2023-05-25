package com.codea.member;

import com.codea.member.Member;
import com.codea.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByEmailAndMemberStatus(String email, Member.MemberStatus status);

}
