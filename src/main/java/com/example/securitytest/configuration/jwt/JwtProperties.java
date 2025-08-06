package com.example.securitytest.configuration.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("jwt")
public class JwtProperties {

  // yml 파일에서 설정한 값 매핑
  private String issuer;
  private String secretKey;

}
