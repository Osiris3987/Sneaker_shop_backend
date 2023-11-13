package com.example.sneaker_shop_backend.service.Impl;

import com.example.sneaker_shop_backend.domain.cart.Cart;
import com.example.sneaker_shop_backend.domain.cart.CartItem;
import com.example.sneaker_shop_backend.domain.exception.ResourceNotFoundException;
import com.example.sneaker_shop_backend.domain.user.Role;
import com.example.sneaker_shop_backend.domain.user.User;
import com.example.sneaker_shop_backend.repository.UserRepository;
import com.example.sneaker_shop_backend.service.CartService;
import com.example.sneaker_shop_backend.service.UserService;
import com.example.sneaker_shop_backend.web.dto.CartItemDto;
import com.example.sneaker_shop_backend.web.dto.UserDto;
import com.example.sneaker_shop_backend.web.mapper.CartItemMapper;
import com.example.sneaker_shop_backend.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CartService cartService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final CartItemMapper cartItemMapper;

    @Override
    @Transactional
    public void create(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new IllegalStateException("User already exists.");
        }
        User user = userMapper.toEntity(userDto, null);
        user.setRoles(Set.of(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Cart cart = cartService.create();
        user.setCart(cart);
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartItemDto> getCartItems(Long userId) {
        User user = getById(userId);
        return cartItemMapper.toDtos(user.getCart().getCartItems());
    }

    @Override
    public boolean isCartOwner(Long userId, Long cartId) {
        return userRepository.isCartOwner(userId, cartId);
    }

    @Override
    @Transactional
    public void removeCartItem(Long itemId, Long userId) {
        User user = getById(userId);
        List<CartItem> cartItems = user.getCart().getCartItems().stream()
                .filter(cartItem -> cartItem.getItemId().equals(itemId))
                .toList();
        user.getCart().getCartItems().removeAll(cartItems);
        userRepository.save(user);
    }
}
