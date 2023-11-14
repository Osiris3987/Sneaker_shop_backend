package com.example.sneaker_shop_backend.repository;

import com.example.sneaker_shop_backend.domain.entity.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
