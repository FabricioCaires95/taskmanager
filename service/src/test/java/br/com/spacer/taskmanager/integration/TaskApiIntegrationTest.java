package br.com.spacer.taskmanager.integration;

import static br.com.spacer.taskmanager.utils.TestConstants.DEFAULT_ID;
import static br.com.spacer.taskmanager.utils.TestDataCreator.newCreateTaskDTO;
import static br.com.spacer.taskmanager.utils.TestDataCreator.newTask;
import static br.com.spacer.taskmanager.utils.TestDataCreator.newUpdateTaskDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import br.com.spacer.taskmanager.api.facade.TaskApi;
import br.com.spacer.taskmanager.core.BaseIntegrationTest;
import br.com.spacer.taskmanager.domain.repository.TaskRepository;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

class TaskApiIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private TaskApi api;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    protected void setupEach() throws Exception {
        setLocalHostBasePath(api.getApiClient(), "/v1");
    }

    @Test
    void testCreateTaskSuccess() {
        var createTaskDto = newCreateTaskDTO();

        var entitySaved = api.createTask(createTaskDto);

        assertNotNull(entitySaved);
        assertNotNull(entitySaved.getFinishAt());
        assertNotNull(entitySaved.getCreatedAt());
        assertFalse(entitySaved.getIsFinished());
        assertNotNull(entitySaved.getId());
        assertEquals(createTaskDto.getDescription(), entitySaved.getDescription());
        assertEquals(createTaskDto.getTitle(), entitySaved.getTitle());
    }

    @Test
    void testGetTaskByIdSuccess() {
        var task = taskRepository.saveAndFlush(newTask().build());

        var result = api.getTask(task.getId());

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(result.getTitle(), task.getTitle());
        assertEquals(result.getDescription(), task.getDescription());
        assertFalse(result.getIsFinished());
    }

    @Test
    void testGetErrorWhenTaskNotFound() {
        assertThrows(
            HttpClientErrorException.NotFound.class,
            () -> api.getTask(DEFAULT_ID));
    }

    @Test
    void testUpdateTaskSuccess() {
        var task = taskRepository.saveAndFlush(newTask().build());

        var updateTaskDTO = newUpdateTaskDTO()
                .description(task.getDescription().concat("-desc test"))
                .title(task.getTitle().concat("-title test"))
                .finishAt(task.getFinishAt().plusDays(3));

        api.updateTask(task.getId(), updateTaskDTO);

        var taskUpdated = taskRepository.findById(task.getId()).orElseThrow();

        assertNotNull(taskUpdated);
        assertEquals(taskUpdated.getTitle(), updateTaskDTO.getTitle());
        assertEquals(taskUpdated.getDescription(), updateTaskDTO.getDescription());
        assertEquals(taskUpdated.getFinishAt(), updateTaskDTO.getFinishAt());
    }

    @Test
    void testUpdateTaskIdNotExist() {
        assertThrows(
                HttpClientErrorException.class,
                () -> api.updateTask(DEFAULT_ID, newUpdateTaskDTO())
        );
    }

    @Test
    void testErrorWhenTitleDoesNotExist() {
        var task = taskRepository.saveAndFlush(newTask().build());
        assertThrows(
                HttpClientErrorException.class,
                () -> api.updateTask(task.getId(), newUpdateTaskDTO().title(StringUtils.EMPTY))
        );
    }
}
