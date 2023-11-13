package com.example.sneaker_shop_backend.web.controller;

import com.example.sneaker_shop_backend.service.ItemService;
import com.example.sneaker_shop_backend.web.dto.ItemDto;
import com.example.sneaker_shop_backend.web.dto.validation.OnCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/items")
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    @PreAuthorize("@customSecurityExpression.hasAdminRole()")
    public void create(@Validated(OnCreate.class) @RequestBody ItemDto itemDto){
        itemService.create(itemDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@customSecurityExpression.hasAdminRole()")
    public void delete(@PathVariable Long id){
        itemService.delete(id);
    }

}
