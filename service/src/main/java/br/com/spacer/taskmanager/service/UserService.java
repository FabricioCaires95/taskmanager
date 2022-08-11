package br.com.spacer.taskmanager.service;

import org.springframework.stereotype.Service;
import br.com.spacer.taskmanager.api.model.CreateUserDTO;
import br.com.spacer.taskmanager.api.model.UserDTO;
import br.com.spacer.taskmanager.domain.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(CreateUserDTO createUserDTO) {
        //TODO
        return null;
    }

}
