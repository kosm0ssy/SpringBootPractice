package com.example.securitytest.service.impl;

import com.example.securitytest.configuration.jwt.JwtTokenProvider;
import com.example.securitytest.entity.User;
import com.example.securitytest.service.RefreshTokenService;
import com.example.securitytest.service.TokenService;
import com.example.securitytest.service.UserService;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

  private final JwtTokenProvider tokenProvider;
  private final RefreshTokenService refreshTokenService;
  private final UserService userService;
  private final RefreshTokenService tokenService;

  @Override
  public String createNewAccessToken(String refreshToken) throws IllegalAccessException {
    if (!tokenProvider.validToken(refreshToken)) {
      throw new IllegalAccessException("Invalid refresh token");
    }

    Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
//    User user = userService.findById(userId);
    User user = tokenService.findByUserId(userId);

    return tokenProvider.generateToken(user, Duration.ofHours(2));
  }
}
