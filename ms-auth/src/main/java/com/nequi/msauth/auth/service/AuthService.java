package com.nequi.msauth.auth.service;

import com.nequi.msauth.auth.jwt.JwtService;
import com.nequi.msauth.auth.util.AuthResponse;
import com.nequi.msauth.auth.util.LoginRequest;
import com.nequi.msauth.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public record AuthService(UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager) {

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByEmail(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

}
