package com.example.securitytest.service;

import com.example.securitytest.dto.UserLoginDto;
import com.example.securitytest.entity.User;
import com.example.securitytest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new IllegalArgumentException(username + " not found"));
  }

  public Long register(UserLoginDto request) {
    return userRepository.save(User.builder()
        .username(request.getUsername())
        .password(bCryptPasswordEncoder.encode(request.getPassword()))
        .build()).getId();
  }

}
