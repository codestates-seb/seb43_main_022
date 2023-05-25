package com.codea.auth.userdetails;

import com.codea.auth.utils.CustomAuthorityUtils;
import com.codea.common.exception.BusinessLogicException;
import com.codea.common.exception.ExceptionCode;
import com.codea.member.Member;
import com.codea.member.MemberRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Optional;

@Component
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final CustomAuthorityUtils authorityUtils;
    private final RedisTemplate redisTemplate;

    public MemberDetailsService(MemberRepository memberRepository, CustomAuthorityUtils authorityUtils,
                                RedisTemplate redisTemplate) {
        this.memberRepository = memberRepository;
        this.authorityUtils = authorityUtils;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findByEmailAndMemberStatus(email, Member.MemberStatus.MEMBER_ACTIVE);
        Member findMember = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return new MemberDetails(findMember);
    }

    private final class MemberDetails extends Member implements UserDetails {
        MemberDetails(Member member) {
            setMemberId(member.getMemberId());
            setEmail(member.getEmail());
            setPassword(member.getPassword());
            setRoles(member.getRoles());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorityUtils.createAuthorities(this.getRoles());
        }

        @Override
        public String getUsername() {
            return getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }


    public void logout(HttpServletRequest request, HttpServletResponse response) {
        String accessJws = request.getHeader("Authorization");

        redisTemplate.delete(accessJws);
        response.setHeader("Authorization", "");
    }

}
