package com.example.sneaker_shop_backend.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ItemImageDto {
    @NotNull(message = "Image must not be null")
    private MultipartFile file;
}
