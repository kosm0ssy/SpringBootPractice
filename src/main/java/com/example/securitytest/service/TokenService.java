package com.example.securitytest.service;

import com.example.securitytest.configuration.jwt.JwtTokenProvider;
import com.example.securitytest.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenService {

  private final RefreshTokenService refreshTokenService;
  private final JwtTokenProvider jwtTokenProvider;
  private final UserService userService;

  public String CreateNewAccessToken(String refreshToken) throws IllegalArgumentException {
    if (!jwtTokenProvider.validToken(refreshToken)) {
      throw new IllegalArgumentException("Invalid refresh token");
    }

    Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
    User user = userService.findbyId(userId);
  }

}
