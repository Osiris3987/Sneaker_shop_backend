package com.example.sneaker_shop_backend.domain.cart;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cart_item")
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long itemId;
    private Integer size;
    private Integer amount;

    @ManyToOne
    private Cart cart;
}
