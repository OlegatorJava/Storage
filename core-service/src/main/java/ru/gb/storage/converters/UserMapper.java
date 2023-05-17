package ru.gb.storage.converters;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.gb.storage.api.UserDto;
import ru.gb.storage.entities.User;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    public User userToDto(UserDto userDto);
    @InheritInverseConfiguration
  public UserDto fromUser(User user);

}
