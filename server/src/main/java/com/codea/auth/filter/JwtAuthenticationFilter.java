package com.codea.auth.filter;


import com.codea.auth.dto.LoginDto;
import com.codea.auth.jwt.JwtTokenizer;
import com.codea.member.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

// JWT(JSON Web Token)을 사용해 인증 처리
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager; // 인증관리자, 인증 요청을 처리 하고 인증 성공 여부 결정
    private final JwtTokenizer jwtTokenizer; // jwt 토큰을 생성하고 관리
    private final RedisTemplate redisTemplate;


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenizer jwtTokenizer,
                                   RedisTemplate redisTemplate) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenizer = jwtTokenizer;
        this.redisTemplate = redisTemplate;
    }


    @SneakyThrows // 체크된 예외를 무시하고 런타임 예외로 변환
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        ObjectMapper objectMapper = new ObjectMapper(); // json 객체를 java 객체로 변환
        LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class);
        // 클라이언트로부터 전달된 인증 요청 정보를 request.getInputStream() 으로 읽어온 뒤 objectMapper를 사용해 LoginDto 클래스의 인스턴스로 변환

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        // loginDto에서 이메일과 패스워드 정보를 가져와 UsernamePasswordAuthenticationToken 인스턴스를 생성, 이 토큰은 인증과정에서 사용

        return authenticationManager.authenticate(authenticationToken);
        // authenticationManager를 사용해 authenticationToken을 인증하고 결과를 반환
        // 인증이 성공하면 Authentication 객체가 반환되고, 실패하면 예외 발생
    }


    @Override // 인증과정이 성공할시 호출
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws SecurityException, IOException {
        Member member = (Member) authResult.getPrincipal(); // Authentication authResult 로부터 인증된 사용자의 정보를 가져옴

        String accessToken = delegateAccessToken(member); // 엑세스 토큰 발급
        String refreshToken = delegateRefreshToken(member); // 리프레시 토큰 발급

        response.setHeader("Authorization", "Bearer " + accessToken); // Authorization 헤더에 추가하여 사용자에게 전달
        response.setHeader("Refresh", refreshToken); // Refresh 헤더에 추가하여 사용자에게 전달

        System.out.println("---------------Access token--------------- \n" + accessToken);
        System.out.println("---------------Refresh token--------------- \n" + refreshToken);
        System.out.println("---------------response--------------- \n" + response);

        //redisTemplate.opsForValue().set(accessToken, refreshToken,  Duration.ofSeconds(30000));
    }

    // 엑세스 토큰 생성 메소드
    private String delegateAccessToken(Member member) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", member.getEmail());
        claims.put("roles", member.getRoles());
        claims.put("nickName", member.getNickName());


        String subject = member.getEmail(); // 이메일을 jwt 토큰의 주체로 설정
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
        // 만료시간 설정

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        // 시크릿키 저장

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);
        // 엑세스 토큰 저장

        return accessToken;
    }

    // 리프레쉬 토큰 생성 메소드
    private String delegateRefreshToken(Member member) {
        String subject = member.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }
}