package com.example.sneaker_shop_backend.service;

import com.example.sneaker_shop_backend.domain.entity.cart.Cart;
import com.example.sneaker_shop_backend.web.dto.CartItemDto;

import java.util.List;

public interface CartService {
    Cart create();
    void addItemToCart(CartItemDto cartItemDto);
    Cart getById(Long id);
    void deleteItemFromCart(CartItemDto cartItemDto);
    CartItemDto getCartItem(Long itemId, Long cartId);
    List<CartItemDto> getCartItems(Long cartId);
}
