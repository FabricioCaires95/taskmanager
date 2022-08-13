package br.com.spacer.taskmanager.service;

import org.springframework.stereotype.Service;
import br.com.spacer.taskmanager.api.model.CreateUserDTO;
import br.com.spacer.taskmanager.api.model.UserDTO;
import br.com.spacer.taskmanager.domain.repository.UserRepository;
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
        //TODO
        return null;
    }

}
