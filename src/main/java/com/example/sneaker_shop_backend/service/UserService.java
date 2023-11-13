package com.example.sneaker_shop_backend.service;

import com.example.sneaker_shop_backend.domain.user.User;
import com.example.sneaker_shop_backend.web.dto.CartItemDto;
import com.example.sneaker_shop_backend.web.dto.UserDto;

import java.util.List;

public interface UserService{
    void create(UserDto userDto);

    User getById(Long id);

    User getByUsername(String username);

    boolean isCartOwner(Long userId, Long cartId);

    List<CartItemDto> getCartItems(Long userId);
    void removeCartItem(Long itemId, Long userId);
}
