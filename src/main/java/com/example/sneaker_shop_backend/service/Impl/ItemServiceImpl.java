package com.example.sneaker_shop_backend.service.Impl;

import com.example.sneaker_shop_backend.repository.ItemRepository;
import com.example.sneaker_shop_backend.service.ItemService;
import com.example.sneaker_shop_backend.web.dto.ItemDto;
import com.example.sneaker_shop_backend.web.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    @Override
    public void create(ItemDto itemDto) {
        itemRepository.save(itemMapper.toEntity(itemDto, null));
    }

    @Override
    public void delete(Long itemId) {
        itemRepository.deleteById(itemId);
    }

    @Override
    public List<ItemDto> getAllItems() {
        return itemMapper.toDtos(itemRepository.findAll());
    }
}
