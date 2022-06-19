package br.com.spacer.taskmanager.service;

import java.util.UUID;
import org.springframework.stereotype.Service;
import br.com.spacer.taskmanager.api.model.CreateTaskDTO;
import br.com.spacer.taskmanager.api.model.TaskDTO;
import br.com.spacer.taskmanager.api.model.UpdateTaskDTO;
import br.com.spacer.taskmanager.domain.repository.TaskRepository;
import br.com.spacer.taskmanager.exception.TaskNotFoundException;
import br.com.spacer.taskmanager.mapper.TaskMapper;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public TaskDTO getTaskById(UUID id) {
        var task = taskRepository
                .findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        return taskMapper.fromEntityToDto(task);
    }

    public TaskDTO createTask(CreateTaskDTO createTaskDTO) {
        var task = taskMapper.fromCreateDtoToEntity(createTaskDTO);
        var savedTask = taskRepository.save(task);

        return taskMapper.fromEntityToDto(savedTask);
    }

    public void updateTask(UUID id, UpdateTaskDTO updateTaskDTO) {
        //TODO
    }

    public void deleteTask(UUID id) {
        //TODO
    }

}
