package com.example.sneaker_shop_backend.web.mapper;

import com.example.sneaker_shop_backend.domain.cart.CartItem;
import com.example.sneaker_shop_backend.web.dto.CartItemDto;
import com.example.sneaker_shop_backend.web.mapper.abstract_mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiConsumer;

@Component
@RequiredArgsConstructor
public class CartItemMapper implements Mapper<CartItem, CartItemDto> {
    @Override
    public CartItemDto toDto(CartItem entity) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setItemId(entity.getItemId());
        cartItemDto.setCartId(entity.getCart().getId());
        cartItemDto.setSize(entity.getSize());
        cartItemDto.setAmount(entity.getAmount());
        return cartItemDto;
    }

    @Override
    public CartItem toEntity(CartItemDto dto, BiConsumer<CartItem, CartItemDto> block) {
        CartItem cartItem = new CartItem();
        cartItem.setSize(dto.getSize());
        cartItem.setAmount(dto.getAmount());
        cartItem.setItemId(dto.getItemId());
        block.accept(cartItem, dto);
        return cartItem;
    }

    @Override
    public List<CartItemDto> toDtos(List<CartItem> entities) {
        return entities.stream().map(this::toDto).toList();
    }

    @Override
    public List<CartItem> toEntities(List<CartItemDto> dtos) {
        return dtos.stream().map(dto -> toEntity(dto, null)).toList();
    }
}
