package com.example.sneaker_shop_backend.web.mapper;

import com.example.sneaker_shop_backend.domain.entity.item.ItemImage;
import com.example.sneaker_shop_backend.web.dto.ItemImageDto;
import com.example.sneaker_shop_backend.web.mapper.abstract_mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiConsumer;

@Component
public class ItemImageMapper implements Mapper<ItemImage, ItemImageDto> {
    @Override
    public ItemImageDto toDto(ItemImage entity) {
        ItemImageDto itemImageDto = new ItemImageDto();
        itemImageDto.setFile(entity.getFile());
        return itemImageDto;
    }

    @Override
    public ItemImage toEntity(ItemImageDto dto, BiConsumer<ItemImage, ItemImageDto> block) {
        ItemImage itemImage = new ItemImage();
        itemImage.setFile(dto.getFile());
        return itemImage;
    }

    @Override
    public List<ItemImageDto> toDtos(List<ItemImage> entities) {
        return entities.stream().map(this::toDto).toList();
    }

    @Override
    public List<ItemImage> toEntities(List<ItemImageDto> dtos) {
        return dtos.stream().map(d -> toEntity(d, null)).toList();
    }
}
