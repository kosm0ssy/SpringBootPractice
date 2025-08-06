package com.example.securitytest.configuration.jwt;

import com.example.securitytest.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtTokenProvider {

  private final JwtProperties jwtProperties;

  public String generateToken(User user, Duration expiredAt) {
    Date now = new Date();
    return makeToken(user, new Date(now.getTime() + expiredAt.toMillis()));
  }

  // 사용자 정보와 유효기간을 입력 받아 토큰 생성
  private String makeToken(User user, Date expiry) {
    Date now = new Date();

    return Jwts.builder()
        .claim("id", user.getId())
        .compact();
  }

  // 토큰을 인자로 받아 유효성 검증
  private boolean validateToken(String token) {
    try {
      // JWT 파싱할 준비하는 객체 생성
      Jwts.parser()
          //비밀키를 가지고 와서 JWT의 signature 검증에 사용될 secret key 설정
          .setSigningKey(jwtProperties.getSecretKey())
          // 전달된 token 파싱 및 서명 검증
          .build() // JJWT Library 상위 버전에서 추가해야함
          .parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      // 유효하면 true, 아닐 시 오류 처리
      throw new RuntimeException(e);
    }
  }

  // jwt token 기반 security authentication 객체 생성
  public Authentication getAuthentication(String token) {
    Claims claims = getClaims(token);

    String role = claims.get("role", String.class);

    // 사용자 권한(역할) 나타냄
    Set<SimpleGrantedAuthority> authorities = Collections.singleton(
        new SimpleGrantedAuthority(claims.get("role").toString()));
    return new UsernamePasswordAuthenticationToken(
        new org.springframework.security.core.userdetails.User(claims.getSubject(), "",
            authorities),
        token,
        authorities
    );
  }

  // token으로 사용자 id 반환
  public Long getUserId(String token) {
    Claims claims = getClaims(token);
    return claims.get("id", Long.class);
  }

  private Claims getClaims(String token) {
    return Jwts.parser()
        .setSigningKey(jwtProperties.getSecretKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

}
