package br.com.spacer.taskmanager.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import br.com.spacer.taskmanager.api.facade.TasksApi;
import br.com.spacer.taskmanager.api.model.CreateTaskDTO;
import br.com.spacer.taskmanager.api.model.TaskDTO;
import br.com.spacer.taskmanager.service.TaskService;
import br.com.spacer.taskmanager.utils.ResponseEntityUtils;

@RestController
public class TaskController implements TasksApi {

    private final Executor executor;
    private final TaskService taskService;

    public TaskController(Executor executor, TaskService taskService) {
        this.executor = executor;
        this.taskService = taskService;
    }

    @Override
    public CompletableFuture<ResponseEntity<TaskDTO>> createTask(CreateTaskDTO createTaskDTO) {
        return CompletableFuture.supplyAsync(() -> taskService.createTask(createTaskDTO), executor)
                .thenApply(ResponseEntityUtils::created);
    }
}
