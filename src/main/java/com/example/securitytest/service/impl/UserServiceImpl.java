package com.example.securitytest.service.impl;

import com.example.securitytest.entity.User;
import com.example.securitytest.repository.UserRepository;
import com.example.securitytest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  // private final PasswordEncoder passwordEncoder;

  @Override
  public void register(String username, String password) {
    if (userRepository.findByUsername(username).isPresent()) {
      throw new RuntimeException("Username is already in use");
    }

    User user = User.builder()
        .username(username)
        .password(password)
        //.userRole("ADMIN")
        .build();

    userRepository.save(user);

  }

  @Override
  public User findByUsername(String username) {
    return null;
  }
}
