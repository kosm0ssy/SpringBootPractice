package com.example.securitytest.service;


import com.example.securitytest.entity.User;

public interface UserService {
    void register(String username, String password);
    User findByUsername(String username);
}
