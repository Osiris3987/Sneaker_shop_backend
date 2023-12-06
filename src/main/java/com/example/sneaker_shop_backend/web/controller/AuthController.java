package com.example.sneaker_shop_backend.web.controller;

import com.example.sneaker_shop_backend.service.AuthService;
import com.example.sneaker_shop_backend.service.UserService;
import com.example.sneaker_shop_backend.web.dto.UserDto;
import com.example.sneaker_shop_backend.web.dto.auth.JwtRequest;
import com.example.sneaker_shop_backend.web.dto.auth.JwtResponse;
import com.example.sneaker_shop_backend.web.dto.validation.OnCreate;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
@Tag(name = "Auth controller", description = "Auth API")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    Counter loginCounter = Metrics.counter("api.auth.login");
    Counter registerCounter = Metrics.counter("api.auth.register");
    Counter refreshCounter = Metrics.counter("api.auth.refresh");

    @Timed("auth.login")
    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody JwtRequest loginRequest) {
        loginCounter.increment();
        return authService.login(loginRequest);
    }

    @Timed("auth.register")
    @PostMapping("/register")
    public UserDto register(@Validated(OnCreate.class)
                            @RequestBody final UserDto userDto) {
        registerCounter.increment();
        userService.create(userDto);
        return userDto;
    }

    @Timed("auth.refresh")
    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken) {
        refreshCounter.increment();
        return authService.refresh(refreshToken);
    }
}
