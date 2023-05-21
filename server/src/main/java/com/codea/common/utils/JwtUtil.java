package com.codea.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class JwtUtil {

    @Value("${jwt.key}")
    private String secretKey;

    public String extractEmailFromToken(String token) {

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        String encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));


        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(encodedSecretKey)
                .parseClaimsJws(token);

        return claimsJws.getBody().get("username", String.class);
    }
}