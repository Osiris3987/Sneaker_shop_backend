package com.example.sneaker_shop_backend.repository;

import com.example.sneaker_shop_backend.domain.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
