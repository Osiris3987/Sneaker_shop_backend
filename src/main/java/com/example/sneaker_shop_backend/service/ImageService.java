package com.example.sneaker_shop_backend.service;

import com.example.sneaker_shop_backend.domain.entity.item.ItemImage;

public interface ImageService {
    String upload(ItemImage image);
}
