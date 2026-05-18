package net.javaguides.springboot.service;

import net.javaguides.springboot.dto.LoginDto;
import net.javaguides.springboot.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
