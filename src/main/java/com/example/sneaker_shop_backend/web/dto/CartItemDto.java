package com.example.sneaker_shop_backend.web.dto;

import com.example.sneaker_shop_backend.web.dto.validation.OnCreate;
import com.example.sneaker_shop_backend.web.dto.validation.OnDelete;
import com.example.sneaker_shop_backend.web.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartItemDto {
    @NotNull(message = "item id must not be null", groups = {OnCreate.class, OnDelete.class, OnUpdate.class})
    private Long itemId;
    @NotNull(message = "cart id must not be null", groups = {OnCreate.class, OnDelete.class, OnUpdate.class})
    private Long cartId;
    @NotNull(message = "size must not be null", groups = {OnCreate.class, OnDelete.class, OnUpdate.class})
    private Integer size;
    @NotNull(message = "amount must not be null", groups = {OnCreate.class, OnDelete.class, OnUpdate.class})
    private Integer amount;
}
