package com.example.securitytest.service;

import com.example.securitytest.entity.RefreshToken;
import java.util.Optional;

public interface RefreshTokenService {

  Optional<RefreshToken> findByUserId(Long userId);

  // refreshToken을 전달 받아 새로운 accessToken 생성
  RefreshToken findByRefreshToken(String refreshToken);

}
