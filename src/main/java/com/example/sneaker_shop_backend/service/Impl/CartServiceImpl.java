package com.example.sneaker_shop_backend.service.Impl;

import com.example.sneaker_shop_backend.domain.cart.Cart;
import com.example.sneaker_shop_backend.domain.cart.CartItem;
import com.example.sneaker_shop_backend.domain.exception.ResourceNotFoundException;
import com.example.sneaker_shop_backend.repository.CartItemRepository;
import com.example.sneaker_shop_backend.repository.CartRepository;
import com.example.sneaker_shop_backend.service.CartService;
import com.example.sneaker_shop_backend.web.dto.CartItemDto;
import com.example.sneaker_shop_backend.web.mapper.CartItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
    @Override
    @Transactional
    public Cart create() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void addItemToCart(CartItemDto cartItemDto) {
        CartItem cartItem = cartItemMapper.toEntity(cartItemDto, (cartItemEntity, dto) -> {
            cartItemEntity.setCart(cartRepository.findById(dto.getCartId()).orElseThrow());
        });
        cartItemRepository.save(cartItem);
    }

    @Override
    @Transactional(readOnly = true)
    public Cart getById(Long id) {
        return cartRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cart not found")
        );
    }

    @Override
    @Transactional
    public void deleteItemFromCart(CartItemDto cartItemDto) {
        Cart cart = getById(cartItemDto.getCartId());
        List<CartItem> forRemove = cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getItemId().equals(cartItemDto.getItemId()))
                .toList();
        cart.getCartItems().removeAll(forRemove);
        cartRepository.save(cart);
    }
}
