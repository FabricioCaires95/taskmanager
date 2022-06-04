package br.com.spacer.taskmanager.mapper;

import br.com.spacer.taskmanager.api.model.CreateTaskDTO;
import br.com.spacer.taskmanager.api.model.TaskDTO;
import br.com.spacer.taskmanager.domain.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task fromCreateDtoToEntity(CreateTaskDTO createTaskDTO);

    TaskDTO fromEntityToDto(Task task);
}
