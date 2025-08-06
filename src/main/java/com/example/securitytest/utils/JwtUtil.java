package com.example.securitytest.utils;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(String username){
        Date now = new Date();
        Date exp = new Date(now.getTime() + expiration);

        return Jwts.builder();
    }
}
