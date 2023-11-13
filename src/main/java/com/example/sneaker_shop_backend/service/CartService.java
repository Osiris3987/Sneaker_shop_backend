package com.example.sneaker_shop_backend.service;

import com.example.sneaker_shop_backend.domain.cart.Cart;
import com.example.sneaker_shop_backend.web.dto.CartItemDto;

public interface CartService {
    Cart create();
    void addItemToCart(CartItemDto cartItemDto);
    Cart getById(Long id);
    void deleteItemFromCart(CartItemDto cartItemDto);
}
