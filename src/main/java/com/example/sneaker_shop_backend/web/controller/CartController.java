package com.example.sneaker_shop_backend.web.controller;

import com.example.sneaker_shop_backend.service.CartService;
import com.example.sneaker_shop_backend.web.dto.CartItemDto;
import com.example.sneaker_shop_backend.web.dto.validation.OnCreate;
import com.example.sneaker_shop_backend.web.dto.validation.OnDelete;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
@Validated
public class CartController {
    private final CartService cartService;

    @PutMapping("/items")
    @PreAuthorize("@customSecurityExpression.canAccessCart(#cartItemDto.getCartId)")
    public void addItem(@Validated(OnCreate.class) @RequestBody CartItemDto cartItemDto){
        cartService.addItemToCart(cartItemDto);
    }

    @DeleteMapping("/items")
    @PreAuthorize("@customSecurityExpression.canAccessCart(#cartItemDto.getCartId())")
    public void deleteCartItem(@Validated(OnDelete.class) @RequestBody CartItemDto cartItemDto){
        cartService.deleteItemFromCart(cartItemDto);
    }
}
