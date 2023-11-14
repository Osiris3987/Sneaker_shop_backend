package com.example.sneaker_shop_backend.service;

import com.example.sneaker_shop_backend.domain.item.ItemImage;
import com.example.sneaker_shop_backend.web.dto.ItemDto;

import java.util.List;

public interface ItemService {
    void create(ItemDto itemDto);
    void delete(Long itemId);
    ItemDto getById(Long id);
    List<ItemDto> getAllItems();
    void uploadImage(Long id, ItemImage itemImage);
}
