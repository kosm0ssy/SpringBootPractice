package com.example.securitytest.service;

public interface TokenService {

  String createNewAccessToken(String refreshToken) throws IllegalAccessException;

}
