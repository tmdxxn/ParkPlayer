package com.movie.parkplayer.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

// 김승준
@Component
@Slf4j
public class JWTTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private Key secretKey;

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String username) {
        log.debug("Generating token for username: {}", username);
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        try {
            log.debug("Parsing token: {}", token);
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            log.error("잘못된 JWT 토큰", e);
            return null;
        }
    }

    public boolean validateToken(String token) {
        String username = getUsernameFromToken(token);
        boolean isValid = (username != null && !isTokenExpired(token));
        log.debug("토큰 유효성 검사 결과 : {}", isValid);
        return isValid;
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        boolean isExpired = expiration.before(new Date());
        log.debug("토큰 만료 여부 : {}", isExpired);
        return isExpired;
    }
}

