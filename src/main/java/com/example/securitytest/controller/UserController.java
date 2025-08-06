package com.example.securitytest.controller;

import com.example.securitytest.dto.UserLoginDto;
import com.example.securitytest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @GetMapping("/login")
  public String login() {
    return "/login";
  }

  @GetMapping("/register")
  public String register() {
    return "/register";
  }

  @PostMapping("/user")
  public String register(UserLoginDto request) {
    userService.register(request);
    return "redirect:/login";
  }

}
