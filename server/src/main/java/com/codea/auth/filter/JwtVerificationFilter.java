package com.codea.auth.filter;

import com.codea.auth.handler.MemberAuthenticationEntryPoint;
import com.codea.auth.jwt.JwtTokenizer;
import com.codea.auth.utils.CustomAuthorityUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException; //주의 - org.security에 똑같은 SignatureException 있음
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.codea.auth.utils.ErrorResponder.sendErrorResponse;

public class JwtVerificationFilter extends OncePerRequestFilter {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final MemberAuthenticationEntryPoint memberAuthenticationEntryPoint;

    public JwtVerificationFilter(JwtTokenizer jwtTokenizer, CustomAuthorityUtils authorityUtils,
                                 MemberAuthenticationEntryPoint memberAuthenticationEntryPoint) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
        this.memberAuthenticationEntryPoint = memberAuthenticationEntryPoint;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            Map<String, Object> claims = verifyJws(request); // jwt 토큰의 claims를 검증 및 반환
            setAuthenticationToContext(claims); // SecurityContextHolder에 설정
            filterChain.doFilter(request, response); // 필터체인으로 요청 전달
        } catch (SignatureException se) { // jwt 서명 오류시 발생하는 예외 처리
            sendErrorResponse(response, HttpStatus.valueOf(401));
        } catch (ExpiredJwtException ee) { // jwt 토큰 만료시 발생하는 예외 처리
            AuthenticationException authException = new AuthenticationException("Access token expired") {};
            memberAuthenticationEntryPoint.commence(request, response, authException);
        } catch (Exception e) { // 기타 예외 발생시 예외 처리, 필터체인으로 요청 전달
            request.setAttribute("exception", e);
            filterChain.doFilter(request, response);
        }
    }


    @Override // 특정 요청이 이 필터를 건너 뛰어야 하는지 여부를 결정하는 메소드
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String authorization = request.getHeader("Authorization");

        return authorization == null || !authorization.startsWith("Bearer");
        // authorization의 값이 null 이거나 Bearer로 시작하지 않는 경우 필터를 건너 뛰어야 한다고 판단하고 true를 반환
        // jwt 토큰이 있는 요청에만 이 필터가 적용 되도록 하기 위함
        // jwt 토큰이 필요하지 않은 요청에 대해서는 불필요한 필터 작업이 수행되지 않음
    }

    // jwt 토큰을 검증하고 토큰에 포함된 claims를 반환하는 역할을 하는 메소드
    private Map<String, Object> verifyJws(HttpServletRequest request) {
        String jws = request.getHeader("Authorization").replace("Bearer ", "");
        // Bearer 문자열을 제거하여 jwt 토큰 문자열만 추출
        System.out.println("---------------jws--------------- \n" + jws);
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        System.out.println("---------------base64encodedSecretKey--------------- \n" + base64EncodedSecretKey);
        Map<String, Object> claims = jwtTokenizer.getClaims(jws, base64EncodedSecretKey).getBody();
        // getClaims 메소드를 사용하여 jwt 토큰을 분석하고 claims를 가져옴
        // 인자로 jwt 토큰 문자열과 secret key 를 전달
        System.out.println("---------------claims--------------- \n" + claims);
        return claims;

        // 전체 인증 필터 과정 중 jwt 토큰을 검증하고 claims를 추출하는 부분을 담당
        // claims를 사용하여 인증 객체를 생성하고 시큐리티 컨텍스트에 설정하는 작업을 수행
    }

    // jwt 토큰으로부터 추출된 claims를 사용하여 인증 객체를 생성하고 시큐리티 컨텍스트에 설정하는 역할을 하는 메소드
    private void setAuthenticationToContext(Map<String, Object> claims) {
        String username = (String) claims.get("email");
        // claims에서 email을 가져옴

        List<GrantedAuthority> authorities = authorityUtils.createAuthorities((List)claims.get("roles"));
        // claims에서 roles를 가져옴
        // authorityUtils.createAuthorities 메소드로 역할 목록을 GrantedAuthority 객체의 목록으로 변환

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
        // username과 roles를 사용하여 인증 객체(Authentication)를 생성
        // 비밀번호는 이미 검증되었으므로 null 설정 됨

        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 시큐리티 컨텍스트에 설정

    }
}