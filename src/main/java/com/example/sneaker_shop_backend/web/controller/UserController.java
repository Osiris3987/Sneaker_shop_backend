package com.example.sneaker_shop_backend.web.controller;

import com.example.sneaker_shop_backend.service.UserService;
import com.example.sneaker_shop_backend.web.dto.CartItemDto;
import com.example.sneaker_shop_backend.web.dto.UserDto;
import com.example.sneaker_shop_backend.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public UserDto getUserById(@PathVariable Long id){
        return userMapper.toDto(userService.getById(id));
    }

    @GetMapping("/{id}/items")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public List<CartItemDto> getCartItemsByUserId(@PathVariable Long id){
        return userService.getCartItems(id);
    }

}
