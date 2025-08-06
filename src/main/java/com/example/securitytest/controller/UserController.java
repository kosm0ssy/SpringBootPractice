package com.example.securitytest.controller;

import com.example.securitytest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

//  @RequestMapping(value = "", method = RequestMethod.POST)
//  public UserDto createUser(@RequestBody UserDto userDto) {
//
//  }

}
