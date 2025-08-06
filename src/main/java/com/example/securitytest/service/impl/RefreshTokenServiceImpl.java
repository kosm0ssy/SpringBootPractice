package com.example.securitytest.service.impl;

import com.example.securitytest.entity.RefreshToken;
import com.example.securitytest.repository.RefreshTokenRepository;
import com.example.securitytest.service.RefreshTokenService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

  private final RefreshTokenRepository refreshTokenRepository;

  @Override
  public Optional<RefreshToken> findByUserId(Long userId) {
    return Optional.empty();
  }

  @Override
  public RefreshToken findByRefreshToken(String refreshToken) {
    return refreshTokenRepository.findByRefreshToken(refreshToken)
        .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
  }

}
