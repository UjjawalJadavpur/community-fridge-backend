package com.fridge.community_fridge_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fridge.community_fridge_backend.dto.AuthResponse;
import com.fridge.community_fridge_backend.dto.LoginRequest;
import com.fridge.community_fridge_backend.dto.RegisterRequest;
import com.fridge.community_fridge_backend.entity.Role;
import com.fridge.community_fridge_backend.entity.User;
import com.fridge.community_fridge_backend.repository.UserRepository;
import com.fridge.community_fridge_backend.security.JwtService;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) 
    {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already registered");
        }
        
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole() != null ? Role.valueOf(request.getRole()) : Role.DONOR);

        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }


    public AuthResponse login(LoginRequest request) 
    {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );

        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
}
