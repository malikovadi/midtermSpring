package com.kg.alatoo.midtermSpring.controllers;

import com.kg.alatoo.midtermSpring.dto.UserDTO;
import com.kg.alatoo.midtermSpring.dto.authorization.*;
import com.kg.alatoo.midtermSpring.entities.RefreshToken;
import com.kg.alatoo.midtermSpring.services.JwtService;
import com.kg.alatoo.midtermSpring.services.RefreshTokenService;
import com.kg.alatoo.midtermSpring.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    @PostMapping("/register")
    public UserDTO register(@Validated @RequestBody RegistrationDTO registrationDTO) {
        return userService.register(registrationDTO);

    }

    @PostMapping("/login")
    public JwtTokenDTO AuthenticateAndGetToken(@RequestBody AuthLoginDTO authLoginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authLoginDTO.getUsername(), authLoginDTO.getPassword()));
        if (authentication.isAuthenticated()) {
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authLoginDTO.getUsername());
            return JwtTokenDTO.builder()
                    .accessToken(jwtService.GenerateToken(authLoginDTO.getUsername()))
                    .token(refreshToken.getToken())
                    .build();

        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @PostMapping("/refreshToken")
    public JwtTokenDTO refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO) {
        return refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(userInfo -> {
                    String accessToken = jwtService.GenerateToken(userInfo.getUsername());
                    return JwtTokenDTO.builder()
                            .accessToken(accessToken)
                            .token(refreshTokenRequestDTO.getToken()).build();
                }).orElseThrow(() -> new RuntimeException("Refresh Token is not in DB..!!"));
    }
}