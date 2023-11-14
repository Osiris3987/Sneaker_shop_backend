package com.example.sneaker_shop_backend.domain.entity.item;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ItemImage {
    private MultipartFile file;
}
