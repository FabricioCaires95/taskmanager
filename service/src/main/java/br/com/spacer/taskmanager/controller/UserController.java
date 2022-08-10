package br.com.spacer.taskmanager.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import br.com.spacer.taskmanager.api.facade.UsersApi;
import br.com.spacer.taskmanager.api.model.CreateUserDTO;
import br.com.spacer.taskmanager.api.model.UserDTO;

@RestController
public class UserController implements UsersApi {

    private final Executor controllersExecutors;

    public UserController(Executor controllersExecutors) {
        this.controllersExecutors = controllersExecutors;
    }

    @Override
    public CompletableFuture<ResponseEntity<UserDTO>> createUser(CreateUserDTO createUserDTO) {
        return UsersApi.super.createUser(createUserDTO);
    }
}
