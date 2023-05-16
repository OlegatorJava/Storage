package ru.gb.storage.converters;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.gb.storage.dto.ProductDto;
import ru.gb.storage.dto.UserDto;
import ru.gb.storage.entities.Product;
import ru.gb.storage.entities.User;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    User toUser(UserDto userDto);

    @InheritInverseConfiguration
    UserDto fromUser(User user);

    List<User> toListUsers(List<UserDto> userDtoList);

    List<UserDto> fromListUsers(List<User> usersList);

}
