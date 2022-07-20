package br.com.spacer.taskmanager.mapper;

import br.com.spacer.taskmanager.api.model.CreateTaskDTO;
import br.com.spacer.taskmanager.api.model.TaskDTO;
import br.com.spacer.taskmanager.api.model.UpdateTaskDTO;
import br.com.spacer.taskmanager.domain.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task fromCreateDtoToEntity(CreateTaskDTO createTaskDTO);

    @Mapping(source = "finished", target = "isFinished")
    TaskDTO fromEntityToDto(Task task);

    Task fromUpdateDtoToEntity(UpdateTaskDTO updateTaskDTO);
}
