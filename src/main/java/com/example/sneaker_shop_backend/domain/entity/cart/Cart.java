package com.example.sneaker_shop_backend.domain.entity.cart;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

    @Entity
    @Table(name = "carts")
    @Data
    public class Cart {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<CartItem> cartItems;
    }
