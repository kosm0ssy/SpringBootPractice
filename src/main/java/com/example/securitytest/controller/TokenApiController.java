package com.example.securitytest.controller;

import com.example.securitytest.dto.CreateAccessTokenRequest;
import com.example.securitytest.dto.CreateAccessTokenResponse;
import com.example.securitytest.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.service.RequestBodyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenApiController {

  public final TokenService tokenService;
  private final RequestBodyService requestBodyBuilder;

  @PostMapping("/api/token")
  public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken(
      @RequestBody CreateAccessTokenRequest request) throws IllegalAccessException {
    String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new CreateAccessTokenResponse(newAccessToken));
  }

}
