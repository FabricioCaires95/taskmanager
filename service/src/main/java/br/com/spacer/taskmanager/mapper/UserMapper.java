package br.com.spacer.taskmanager.mapper;

import br.com.spacer.taskmanager.api.model.CreateUserDTO;
import br.com.spacer.taskmanager.api.model.UpdateUserDTO;
import br.com.spacer.taskmanager.api.model.UserDTO;
import br.com.spacer.taskmanager.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "tasks", ignore = true)
    User fromCreateDtoToEntity(CreateUserDTO createUserDTO);

    @Mapping(target = "tasks", ignore = true)
    UserDTO fromEntityToDto(User user);

    @Mapping(target = "tasks", ignore = true)
    User fromUpdateDtoToEntity(UpdateUserDTO updateUserDTO);
}
