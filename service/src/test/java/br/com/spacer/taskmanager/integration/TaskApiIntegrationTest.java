package br.com.spacer.taskmanager.integration;

import static br.com.spacer.taskmanager.utils.TestDataCreator.newCreateTaskDTO;
import static br.com.spacer.taskmanager.utils.TestDataCreator.newTask;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import br.com.spacer.taskmanager.api.facade.TaskApi;
import br.com.spacer.taskmanager.core.BaseIntegrationTest;
import br.com.spacer.taskmanager.domain.repository.TaskRepository;
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
        assertFalse(entitySaved.getIsFinished());
    }

    @Test
    void testGetTaskByIdSuccess() {
        var task = newTask().build();
        taskRepository.saveAndFlush(task);

        var result = api.getTask(task.getId());

        assertNotNull(result);
        assertEquals(result.getTitle(), task.getTitle());
        assertEquals(result.getDescription(), task.getDescription());
        assertEquals(result.getFinishAt(), task.getFinishAt());
    }
}
