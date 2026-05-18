package net.javaguides.springboot.service.impl;

import net.javaguides.springboot.dto.LoginDto;
import net.javaguides.springboot.dto.RegisterDto;
import net.javaguides.springboot.entity.Role;
import net.javaguides.springboot.entity.UserJWT;
import net.javaguides.springboot.exception.UserManagementAPIException;
import net.javaguides.springboot.repository.RoleRepository;
import net.javaguides.springboot.repository.UserJWTRepository;
import net.javaguides.springboot.security.JwtTokenProvider;
import net.javaguides.springboot.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserJWTRepository userJWTRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;


    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserJWTRepository userJWTRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userJWTRepository = userJWTRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {

        // add check for username exists in database
        if(userJWTRepository.existsByUsername(registerDto.getUsername())){
            throw new UserManagementAPIException(HttpStatus.BAD_REQUEST, "Username is already exists!.");
        }

        // add check for email exists in database
        if(userJWTRepository.existsByEmail(registerDto.getEmail())){
            throw new UserManagementAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        UserJWT user = new UserJWT();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userJWTRepository.save(user);

        return "User registered successfully!.";
    }
}

