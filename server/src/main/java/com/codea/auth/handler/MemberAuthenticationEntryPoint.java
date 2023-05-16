package com.codea.auth.handler;

import com.codea.auth.jwt.JwtTokenizer;
import com.codea.auth.utils.ErrorResponder;
import com.codea.exception.BusinessLogicException;
import com.codea.exception.ExceptionCode;
import com.codea.member.Member;
import com.codea.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class MemberAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Value("${jwt.key}")
    private String secretKey;

    private final RedisTemplate redisTemplate;
    private final JwtTokenizer jwtTokenizer;
    private final MemberRepository memberRepository;

    public MemberAuthenticationEntryPoint(RedisTemplate redisTemplate, JwtTokenizer jwtTokenizer, MemberRepository memberRepository) {
        this.redisTemplate = redisTemplate;
        this.jwtTokenizer = jwtTokenizer;
        this.memberRepository = memberRepository;
    }

    // 사용자 인증이 실패했을때 처리하는 클래스
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
//        Exception exception = (Exception) request.getAttribute("exception");
//        // HttpServletRequest 객체의 속성에서 exception 속성의 값을 가져옴
//
//        ErrorResponder.sendErrorResponse(response, HttpStatus.UNAUTHORIZED);
//        // 클라이언트에게 401 Unauthorized 코드로 응답
//
//        logExceptionMessage(authException, exception);
//        // 로거를 사용하여 워닝 로그를 기록
//
//

        String accessJws = request.getHeader("Authorization").replace("Bearer ", "");
        String refreshJws = request.getHeader("Refresh");

        System.out.println("---------------- Access 토큰 : " + accessJws + "----------------");
        System.out.println("---------------- Refresh 토큰 : " + refreshJws + "----------------");
        if (redisTemplate.keys(refreshJws) == null) {
            Exception exception = (Exception) request.getAttribute("exception");
            ErrorResponder.sendErrorResponse(response, HttpStatus.UNAUTHORIZED);

            logExceptionMessage(authException, exception);
            System.out.println("Refresh 토큰이 만료되었습니다.");
        } else {
            String email = (String) redisTemplate.opsForValue().get(refreshJws);
            String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

            Member member = memberRepository.findByEmail(email).orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
            String newAccessJws = regenerateAccessToken(member);

            response.setHeader("Authorization", "Bearer " + newAccessJws);

            System.out.println("=============== Access 토큰 재발행 ===============" + base64EncodedSecretKey + email);
            System.out.println(member.getEmail() + "의 Access 토큰이 재발행되었습니다.");
            System.out.println("AccessToken : " + "Bearer " + newAccessJws);
        }

        //redis에 refresh 토큰이 존재하면
        //refresh 토큰을 통해 redis에서 email 추출
        //멤버 객체 get
        //액세스 토큰 재발행
        //redis에 refresh 토큰이 존재하지 않으면 exception
    }


    private void logExceptionMessage(AuthenticationException authException, Exception exception) {
        String message = exception != null ? exception.getMessage() : authException.getMessage();
        // exception 객체가 null 이 아닌 경우 해당 예외 메시지를 사용
        // 그렇지 않은경우 authException의 메시지를 사용

        log.warn("Unauthorized error happened: {}", message);
        // 로그 기록
    }

    private String regenerateAccessToken(Member member) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", member.getEmail());
        claims.put("roles", member.getRoles());

        String subject = member.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }


}
