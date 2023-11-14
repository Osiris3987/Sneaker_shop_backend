package com.example.sneaker_shop_backend.web.controller;

import com.example.sneaker_shop_backend.domain.entity.item.ItemImage;
import com.example.sneaker_shop_backend.service.ItemService;
import com.example.sneaker_shop_backend.web.dto.ItemDto;
import com.example.sneaker_shop_backend.web.dto.ItemImageDto;
import com.example.sneaker_shop_backend.web.dto.validation.OnCreate;
import com.example.sneaker_shop_backend.web.mapper.ItemImageMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/items")
@Validated
@Tag(name = "Item Controller", description = "Item API")
public class ItemController {
    private final ItemService itemService;
    private final ItemImageMapper itemImageMapper;

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

    @PostMapping("/{id}/images")
    @PreAuthorize("@customSecurityExpression.hasAdminRole()")
    public void uploadImage(@PathVariable Long id,
                            @Validated @ModelAttribute ItemImageDto itemImageDto){
        ItemImage image = itemImageMapper.toEntity(itemImageDto, null);
        itemService.uploadImage(id, image);
    }

    @GetMapping
    public List<ItemDto> getAllItems(){
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public ItemDto getById(@PathVariable Long id){
        return itemService.getById(id);
    }
}
