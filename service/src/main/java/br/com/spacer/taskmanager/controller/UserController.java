package br.com.spacer.taskmanager.controller;

import static java.util.concurrent.CompletableFuture.runAsync;
import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import br.com.spacer.taskmanager.api.facade.UsersApi;
import br.com.spacer.taskmanager.api.model.CreateUserDTO;
import br.com.spacer.taskmanager.api.model.UpdateUserDTO;
import br.com.spacer.taskmanager.api.model.UserDTO;
import br.com.spacer.taskmanager.service.UserService;
import br.com.spacer.taskmanager.utils.ResponseEntityUtils;

@RestController
public class UserController implements UsersApi {

    private final Executor controllersExecutors;
    private final UserService userService;

    public UserController(Executor controllersExecutors, UserService userService) {
        this.controllersExecutors = controllersExecutors;
        this.userService = userService;
    }

    @Override
    public CompletableFuture<ResponseEntity<UserDTO>> createUser(CreateUserDTO createUserDTO) {
        return supplyAsync(() -> userService.createUser(createUserDTO))
                .thenApply(ResponseEntityUtils::created);
    }

    @Override
    public CompletableFuture<ResponseEntity<UserDTO>> getUser(UUID id) {
        return supplyAsync(() -> userService.getUserById(id), controllersExecutors)
                .thenApply(ResponseEntityUtils::ok);
    }

    @Override
    public CompletableFuture<ResponseEntity<Void>> updateUser(UUID id, UpdateUserDTO updateUserDTO) {
        return runAsync(() -> userService.updateUser(id, updateUserDTO), controllersExecutors)
                .thenApply(ResponseEntityUtils::noContent);
    }
}
