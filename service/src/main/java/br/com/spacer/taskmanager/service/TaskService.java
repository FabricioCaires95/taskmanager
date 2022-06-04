package br.com.spacer.taskmanager.service;

import org.springframework.stereotype.Service;
import br.com.spacer.taskmanager.api.model.CreateTaskDTO;
import br.com.spacer.taskmanager.api.model.TaskDTO;
import br.com.spacer.taskmanager.domain.repository.TaskRepository;
import br.com.spacer.taskmanager.mapper.TaskMapper;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public TaskDTO createTask(CreateTaskDTO createTaskDTO) {
        var task = taskMapper.fromCreateDtoToEntity(createTaskDTO);
        var savedTask = taskRepository.save(task);

        return taskMapper.fromEntityToDto(savedTask);
    }

}
