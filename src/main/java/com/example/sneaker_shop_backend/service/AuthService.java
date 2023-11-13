package com.example.sneaker_shop_backend.service;

import com.example.sneaker_shop_backend.web.dto.auth.JwtRequest;
import com.example.sneaker_shop_backend.web.dto.auth.JwtResponse;

public interface AuthService {
    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);


}
