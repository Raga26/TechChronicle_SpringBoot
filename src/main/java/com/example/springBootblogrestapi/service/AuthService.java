package com.example.springBootblogrestapi.service;

import com.example.springBootblogrestapi.payload.LoginDto;
import com.example.springBootblogrestapi.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
