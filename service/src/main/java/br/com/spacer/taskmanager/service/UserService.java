package br.com.spacer.taskmanager.service;

import java.util.UUID;
import org.springframework.stereotype.Service;
import br.com.spacer.taskmanager.api.model.CreateUserDTO;
import br.com.spacer.taskmanager.api.model.UserDTO;
import br.com.spacer.taskmanager.domain.repository.UserRepository;
import br.com.spacer.taskmanager.exception.UserNotFoundException;
import br.com.spacer.taskmanager.mapper.UserMapper;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO createUser(CreateUserDTO createUserDTO) {
        var user = userRepository.save(userMapper.fromCreateDtoToEntity(createUserDTO));
        return userMapper.fromEntityToDto(user);
    }

    public UserDTO getUserById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::fromEntityToDto)
                .orElseThrow(() -> new UserNotFoundException("User not found "));
    }

}
