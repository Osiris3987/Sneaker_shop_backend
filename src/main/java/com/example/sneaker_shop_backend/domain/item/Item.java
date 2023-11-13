package com.example.sneaker_shop_backend.domain.item;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "items")
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Boolean inStock;

    @OneToMany(cascade = CascadeType.ALL)
    @CollectionTable(name = "items_availability")
    @JoinColumn(name = "availability_id")
    private List<Availability> availability;
}
