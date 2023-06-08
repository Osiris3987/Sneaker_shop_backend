package com.example.sneaker_shop_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.UUID;
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> orders;

    public Cart(List<Item> orders) {
        this.orders = orders;
    }
}
