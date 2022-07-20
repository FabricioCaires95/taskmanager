package br.com.spacer.taskmanager.unit;

import static br.com.spacer.taskmanager.utils.MapperUtils.taskMapper;
import static br.com.spacer.taskmanager.utils.TestConstants.DEFAULT_ID;
import static br.com.spacer.taskmanager.utils.TestDataCreator.newCreateTaskDTO;
import static br.com.spacer.taskmanager.utils.TestDataCreator.newTask;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.spacer.taskmanager.core.BaseUnitTest;
import br.com.spacer.taskmanager.domain.repository.TaskRepository;
import br.com.spacer.taskmanager.exception.TaskNotFoundException;
import br.com.spacer.taskmanager.service.TaskService;
import br.com.spacer.taskmanager.validator.TaskValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class TaskServiceUnitTest extends BaseUnitTest {
    private TaskService victim;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskValidator taskValidator;

    @BeforeEach
    void beforeEach() {
        victim = new TaskService(taskRepository, taskMapper(), taskValidator);
    }

    @Test
    void testCreateTaskSuccess() {
        var createTaskDto = newCreateTaskDTO();
        when(taskRepository.save(any())).thenReturn(newTask().build());

        var taskDto = victim.createTask(createTaskDto);

        assertEquals(taskDto.getTitle(), createTaskDto.getTitle());
        assertEquals(taskDto.getFinishAt(), createTaskDto.getFinishAt());
        assertEquals(taskDto.getDescription(), createTaskDto.getDescription());

        verify(taskRepository).save(any());
    }

    @Test
    void testGetTaskByIdSuccess() {
        var entity = newTask().id(DEFAULT_ID).build();
        when(taskRepository.findById(any())).thenReturn(of(entity));

        var taskDto = victim.getTaskById(DEFAULT_ID);

        assertEquals(taskDto.getDescription(), entity.getDescription());
        assertEquals(taskDto.getTitle(), entity.getTitle());
        assertEquals(taskDto.getFinishAt(), entity.getFinishAt());

        verify(taskRepository).findById(any());
    }

    @Test
    void testGetTaskByIdNotFound() {
        when(taskRepository.findById(any())).thenReturn(empty());;
        assertThrows(TaskNotFoundException.class, () -> victim.getTaskById(DEFAULT_ID));
    }

}
