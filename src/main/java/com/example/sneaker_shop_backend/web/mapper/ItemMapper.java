package com.example.sneaker_shop_backend.web.mapper;

import com.example.sneaker_shop_backend.domain.item.Item;
import com.example.sneaker_shop_backend.web.dto.ItemDto;
import com.example.sneaker_shop_backend.web.mapper.abstract_mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.function.BiConsumer;

@Component
@RequiredArgsConstructor
public class ItemMapper implements Mapper<Item, ItemDto> {

    private final AvailabilityMapper availabilityMapper;

    @Override
    public ItemDto toDto(Item entity) {
        ItemDto itemDto = new ItemDto();
        itemDto.setName(entity.getName());
        itemDto.setDescription(entity.getDescription());
        itemDto.setInStock(entity.getInStock());
        itemDto.setSizeAmount(availabilityMapper.toDtos(entity.getAvailability()));
        itemDto.setImages(entity.getImages());
        return itemDto;
    }

    @Override
    public Item toEntity(ItemDto dto, BiConsumer<Item, ItemDto> block) {
        Item item = new Item();
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setInStock(dto.getInStock());
        item.setAvailability(availabilityMapper.toEntities(dto.getSizeAmount()));
        return item;
    }

    @Override
    public List<ItemDto> toDtos(List<Item> entities) {
        return entities.stream().map(this::toDto).toList();
    }

    @Override
    public List<Item> toEntities(List<ItemDto> dtos) {
        return dtos.stream().map(dto -> toEntity(dto, null)).toList();
    }

}
