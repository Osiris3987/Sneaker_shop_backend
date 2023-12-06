package com.example.sneaker_shop_backend.service.Impl;

import com.example.sneaker_shop_backend.domain.entity.cart.Cart;
import com.example.sneaker_shop_backend.domain.entity.cart.CartItem;
import com.example.sneaker_shop_backend.domain.entity.item.Item;
import com.example.sneaker_shop_backend.domain.exception.AccessDeniedException;
import com.example.sneaker_shop_backend.domain.exception.ResourceNotFoundException;
import com.example.sneaker_shop_backend.repository.CartItemRepository;
import com.example.sneaker_shop_backend.repository.CartRepository;
import com.example.sneaker_shop_backend.repository.ItemRepository;
import com.example.sneaker_shop_backend.service.CartService;
import com.example.sneaker_shop_backend.service.ItemService;
import com.example.sneaker_shop_backend.util.validator.AvailabilityValidator;
import com.example.sneaker_shop_backend.web.dto.AvailabilityDto;
import com.example.sneaker_shop_backend.web.dto.CartItemDto;
import com.example.sneaker_shop_backend.web.dto.ItemDto;
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
    private final AvailabilityValidator availabilityValidator;
    private final ItemRepository itemRepository;
    @Override
    @Transactional
    public Cart create() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void addItemToCart(CartItemDto cartItemDto) {
        Item item = itemRepository.findById(cartItemDto.getItemId()).orElseThrow(()
                -> new ResourceNotFoundException("item not found"));
        if(!availabilityValidator.validate(cartItemDto.getSize(), cartItemDto.getAmount(), item.getAvailability())){
            throw new ResourceNotFoundException("not available");
        }
        CartItem cartItem = cartItemMapper.toEntity(cartItemDto, (cartItemEntity, dto)
                -> cartItemEntity.setCart(getById(dto.getCartId())));
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

    @Override
    @Transactional(readOnly = true)
    public CartItemDto getCartItem(Long cartId, Long itemId) {
        CartItem cartItem = cartItemRepository.findById(itemId).orElseThrow(()
                        -> new ResourceNotFoundException("Cart item not found")
                );
        if (!cartItem.getCart().getId().equals(cartId)){
            throw new AccessDeniedException();
        }
        return cartItemMapper.toDto(cartItem);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartItemDto> getCartItems(Long cartId) {
        Cart cart = getById(cartId);
        return cartItemMapper.toDtos(cart.getCartItems());
    }
}
