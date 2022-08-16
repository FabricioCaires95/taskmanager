package br.com.spacer.taskmanager.service;

import java.util.UUID;
import org.springframework.stereotype.Service;
import br.com.spacer.taskmanager.api.model.CreateUserDTO;
import br.com.spacer.taskmanager.api.model.UserDTO;
import br.com.spacer.taskmanager.domain.repository.UserRepository;
import br.com.spacer.taskmanager.exception.UserNotFoundException;
import br.com.spacer.taskmanager.mapper.UserMapper;
import br.com.spacer.taskmanager.validator.UserValidator;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserValidator userValidator;

    public UserService(UserRepository userRepository, UserMapper userMapper, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userValidator = userValidator;
    }

    public UserDTO createUser(CreateUserDTO createUserDTO) {
        userValidator.validate(createUserDTO);
        var user = userRepository.save(userMapper.fromCreateDtoToEntity(createUserDTO));
        return userMapper.fromEntityToDto(user);
    }

    public UserDTO getUserById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::fromEntityToDto)
                .orElseThrow(() -> new UserNotFoundException("User not found "));
    }

}
