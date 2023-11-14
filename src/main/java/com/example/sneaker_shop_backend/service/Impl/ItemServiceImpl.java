package com.example.sneaker_shop_backend.service.Impl;

import com.example.sneaker_shop_backend.domain.exception.ResourceNotFoundException;
import com.example.sneaker_shop_backend.domain.item.Item;
import com.example.sneaker_shop_backend.domain.item.ItemImage;
import com.example.sneaker_shop_backend.repository.ItemRepository;
import com.example.sneaker_shop_backend.service.ImageService;
import com.example.sneaker_shop_backend.service.ItemService;
import com.example.sneaker_shop_backend.web.dto.ItemDto;
import com.example.sneaker_shop_backend.web.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final ImageService imageService;
    @Override
    public void create(ItemDto itemDto) {
        Item item = itemMapper.toEntity(itemDto, null);
        item.setImages(new ArrayList<>());
        itemRepository.save(item);
    }

    @Override
    public void delete(Long itemId) {
        itemRepository.deleteById(itemId);
    }

    @Override
    public List<ItemDto> getAllItems() {
        return itemMapper.toDtos(itemRepository.findAll());
    }

    @Override
    public void uploadImage(Long id, ItemImage itemImage) {
        Item item = itemRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Item not found")
        );
        String fileName = imageService.upload(itemImage);
        item.getImages().add(fileName);
        itemRepository.save(item);
    }

    @Override
    public ItemDto getById(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Item not found"));
        return itemMapper.toDto(item);
    }
}
