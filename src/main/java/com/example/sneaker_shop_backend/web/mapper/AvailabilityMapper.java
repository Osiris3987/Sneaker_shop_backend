package com.example.sneaker_shop_backend.web.mapper;

import com.example.sneaker_shop_backend.domain.item.Availability;
import com.example.sneaker_shop_backend.web.dto.AvailabilityDto;
import com.example.sneaker_shop_backend.web.mapper.abstract_mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiConsumer;

@Component
public class AvailabilityMapper implements Mapper<Availability, AvailabilityDto> {
    @Override
    public AvailabilityDto toDto(Availability entity) {
        AvailabilityDto availabilityDto = new AvailabilityDto();
        availabilityDto.setSize(entity.getSize());
        availabilityDto.setAmount(entity.getAmount());
        return availabilityDto;
    }

    @Override
    public Availability toEntity(AvailabilityDto dto, BiConsumer<Availability, AvailabilityDto> block) {
        Availability availability = new Availability();
        availability.setSize(dto.getSize());
        availability.setAmount(dto.getAmount());
        return availability;
    }

    @Override
    public List<AvailabilityDto> toDtos(List<Availability> entities) {
        return entities.stream().map(this::toDto).toList();
    }

    @Override
    public List<Availability> toEntities(List<AvailabilityDto> dtos) {
        return dtos.stream().map(dto -> toEntity(dto, null)).toList();
    }
}
