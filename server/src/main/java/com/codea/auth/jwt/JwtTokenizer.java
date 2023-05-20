package com.codea.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


@Component // 스프링빈으로 등록, 스프링컨테이너에서 관리
public class JwtTokenizer {
    @Getter
    @Value("${jwt.key}") // yml 파일에서 긁어옴
    private String secretKey;

    @Getter
    @Value("${jwt.access-token-expiration-minutes}") // yml 파일에서 긁어옴
    private int accessTokenExpirationMinutes;        // 엑세스 토큰 만료 시간

    @Getter
    @Value("${jwt.refresh-token-expiration-minutes}") // yml 파일에서 긁어옴
    private int refreshTokenExpirationMinutes;          // 리프레시 토큰의 만료 시간

    public String encodeBase64SecretKey(String secretKey) {
        return Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));
    } // secretKey Base64 방식으로 인코딩

    public String generateAccessToken(Map<String, Object> claims,
                                      String subject,
                                      Date expiration,
                                      String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        System.out.println(Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration).signWith(key).compact());

        return Jwts.builder() // jwt 빌드 인스턴스 생성
                .setClaims(claims)  // 클레임 데이터 설정  예를들면 ("email", user@example.com" // "role", "admin")
                .setSubject(subject)  // 토큰의 주제(일반적으로 사용자 식별자)를 설정
                .setIssuedAt(Calendar.getInstance().getTime()) // 토큰의 발행 시간을 현재 시간으로 설정
                .setExpiration(expiration) // 만료 시간 설정
                .signWith(key) // 생성된 토큰에 서명할 키 지정
                .compact(); // 서명된 jwt토큰을 URL-sage한 문자열로 압축(URL에 포함될 수 있는 문자들로만 구성된 문자열)
    }

    public String generateRefreshToken(String subject, Date expiration, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    // 검증 후 claims를 반환하는 용도
    public Jws<Claims> getClaims(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);
        return claims;
    }


    // 단순 검증만 하는 용도
    public void verifySignature(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);
    }


    public Date getTokenExpiration(int expirationMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expirationMinutes);
        Date expiration = calendar.getTime();

        return expiration;
    }

    private Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return key;
    }
}