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
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID itemId;
    @ElementCollection
    private List<Short> rating;
    private Integer price;
    private Boolean isInStock;
    public Item(List<Short> rating, Integer price, Boolean isInStock) {
        this.rating = rating;
        this.price = price;
        this.isInStock = isInStock;
    }
}
