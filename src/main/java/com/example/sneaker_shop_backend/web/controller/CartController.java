package com.example.sneaker_shop_backend.web.controller;

import com.example.sneaker_shop_backend.service.CartService;
import com.example.sneaker_shop_backend.web.dto.CartItemDto;
import com.example.sneaker_shop_backend.web.dto.validation.OnCreate;
import com.example.sneaker_shop_backend.web.dto.validation.OnDelete;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
@Validated
@Tag(name = "Cart Controller", description = "Cart API")
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

    @GetMapping("/{id}/items")
    @PreAuthorize("@customSecurityExpression.canAccessCart(#id)")
    public List<CartItemDto> getCartItems(@PathVariable Long id){
        return cartService.getCartItems(id);
    }

    @GetMapping("/{cartId}/items/{itemId}")
    @PreAuthorize("@customSecurityExpression.canAccessCart(#cartId)")
    public CartItemDto getCartItem(@PathVariable Long cartId, @PathVariable Long itemId){
        return cartService.getCartItem(cartId, itemId);
    }
}
