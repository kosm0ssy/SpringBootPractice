package com.example.securitytest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  // SecurityFilterChain interface를 구현하여 보안 필터 체인 구성
//  @Bean
//  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    return http
//        .authorizeHttpRequests((auth) -> auth
//            .requestMatchers("/", "/login").permitAll()
//            .requestMatchers("/admin").hasRole("ADMIN")
//            .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
//            .anyRequest().authenticated())
//        .build();
//  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        // csrf는 원래 자동으로 활성화되도록 설정하지만 개발할 때 잠깐 비활성화
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                "/", "/api/users/login", "api/users/register", "/api/users/user",
                // 공개 경로
                "/swagger-ui/**",               // Swagger UI 정적 리소스
                "/swagger-ui.html",             // Swagger 진입점
                "/v3/api-docs/**"               // OpenAPI 정의
            ).permitAll()
            .requestMatchers("/admin").hasRole("ADMIN")             // 관리자 전용
            .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")  // 공용 사용자
            .anyRequest().authenticated()                           // 그 외 모두 인증
        )
        .formLogin((auth) -> auth
            .loginPage("/login")
            .loginProcessingUrl("/loginProcessing")
            .defaultSuccessUrl("/")
            .permitAll()
        )
        .build();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
