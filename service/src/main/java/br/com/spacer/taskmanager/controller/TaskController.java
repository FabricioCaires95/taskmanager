package br.com.spacer.taskmanager.controller;

import static java.util.concurrent.CompletableFuture.runAsync;
import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import br.com.spacer.taskmanager.api.facade.TasksApi;
import br.com.spacer.taskmanager.api.model.CreateTaskDTO;
import br.com.spacer.taskmanager.api.model.TaskDTO;
import br.com.spacer.taskmanager.api.model.UpdateTaskDTO;
import br.com.spacer.taskmanager.service.TaskService;
import br.com.spacer.taskmanager.utils.ResponseEntityUtils;

@RestController
public class TaskController implements TasksApi {

    private final Executor controllersExecutors;
    private final TaskService taskService;

    public TaskController(Executor controllersExecutors, TaskService taskService) {
        this.controllersExecutors = controllersExecutors;
        this.taskService = taskService;
    }

    @Override
    public CompletableFuture<ResponseEntity<TaskDTO>> getTask(UUID id) {
        return supplyAsync(() -> taskService.getTaskById(id), controllersExecutors)
                .thenApply(ResponseEntityUtils::ok);
    }

    @Override
    public CompletableFuture<ResponseEntity<TaskDTO>> createTask(CreateTaskDTO createTaskDTO) {
        return supplyAsync(() -> taskService.createTask(createTaskDTO), controllersExecutors)
                .thenApply(ResponseEntityUtils::created);
    }

    @Override
    public CompletableFuture<ResponseEntity<Void>> updateTask(UUID id, UpdateTaskDTO updateTaskDTO) {
        return runAsync(() -> taskService.updateTask(id, updateTaskDTO), controllersExecutors)
                .thenApply(ResponseEntityUtils::noContent);
    }

    @Override
    public CompletableFuture<ResponseEntity<Void>> deleteTask(UUID id) {
        return runAsync(() -> taskService.deleteTask(id), controllersExecutors)
                .thenApply(ResponseEntityUtils::noContent);
    }
}
