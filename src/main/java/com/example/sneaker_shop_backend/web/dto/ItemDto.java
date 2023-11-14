package com.example.sneaker_shop_backend.web.dto;

import com.example.sneaker_shop_backend.web.dto.validation.OnCreate;
import com.example.sneaker_shop_backend.web.dto.validation.OnDelete;
import com.example.sneaker_shop_backend.web.dto.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ItemDto {
    @NotNull(message = "Item id must not be null", groups = {OnDelete.class, OnUpdate.class})
    private Long id;
    @NotNull(message = "Item name must not be null", groups = OnCreate.class)
    private String name;

    @NotNull(message = "Item description must not be null", groups = OnCreate.class)
    private String description;

    @NotNull(message = "Is item in stock?", groups = OnCreate.class)
    private Boolean inStock;

    @NotNull(message = "You must indicate size and amount", groups = OnCreate.class)
    private List<AvailabilityDto> sizeAmount;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<String> images;
}
