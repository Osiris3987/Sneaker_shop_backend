package com.example.sneaker_shop_backend.web.mapper;

import com.example.sneaker_shop_backend.domain.entity.user.User;
import com.example.sneaker_shop_backend.web.dto.UserDto;
import com.example.sneaker_shop_backend.web.mapper.abstract_mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiConsumer;

@Component
public class UserMapper implements Mapper<User, UserDto> {

    @Override
    public UserDto toDto(User entity) {
        UserDto userDto = new UserDto();
        userDto.setId(userDto.getId());
        userDto.setName(entity.getName());
        userDto.setUsername(entity.getUsername());
        userDto.setPassword(entity.getPassword());
        return userDto;
    }

    @Override
    public User toEntity(UserDto dto, BiConsumer<User, UserDto> block) {
        User user = new User();
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }

    @Override
    public List<UserDto> toDtos(List<User> entities) {
        return entities.stream().map(this::toDto).toList();
    }

    @Override
    public List<User> toEntities(List<UserDto> dtos) {
        return dtos.stream().map(dto -> toEntity(dto, null)).toList();
    }
}
