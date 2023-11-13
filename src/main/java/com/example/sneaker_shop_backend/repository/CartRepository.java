package com.example.sneaker_shop_backend.repository;

import com.example.sneaker_shop_backend.domain.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
