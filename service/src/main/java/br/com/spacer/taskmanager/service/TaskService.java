package br.com.spacer.taskmanager.service;

import static java.util.Objects.requireNonNull;

import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.spacer.taskmanager.api.model.CreateTaskDTO;
import br.com.spacer.taskmanager.api.model.TaskDTO;
import br.com.spacer.taskmanager.api.model.UpdateTaskDTO;
import br.com.spacer.taskmanager.domain.entity.Task;
import br.com.spacer.taskmanager.domain.repository.TaskRepository;
import br.com.spacer.taskmanager.exception.TaskNotFoundException;
import br.com.spacer.taskmanager.mapper.TaskMapper;
import br.com.spacer.taskmanager.validator.TaskValidator;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TaskValidator taskValidator;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper, TaskValidator taskValidator) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.taskValidator = taskValidator;
    }

    public TaskDTO getTaskById(UUID id) {
        var task = getTaskByIdOrThrowNotFound(id);
        return taskMapper.fromEntityToDto(task);
    }

    public TaskDTO createTask(CreateTaskDTO createTaskDTO) {
        taskValidator.validate(createTaskDTO);
        var task = taskMapper.fromCreateDtoToEntity(createTaskDTO);
        var savedTask = taskRepository.save(task);

        return taskMapper.fromEntityToDto(savedTask);
    }

    @Transactional
    public void updateTask(UUID id, UpdateTaskDTO updateTaskDTO) {
        taskValidator.validate(updateTaskDTO);
        getTaskByIdOrThrowNotFound(id);
        taskRepository.updateTask(id, updateTaskDTO.getTitle(), updateTaskDTO.getDescription(), updateTaskDTO.getFinishAt());
    }

    public void deleteTask(UUID id) {
        //TODO
    }

    private Task getTaskByIdOrThrowNotFound(UUID id) {
        requireNonNull(id);
        return taskRepository
                .findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found "));
    }
}
