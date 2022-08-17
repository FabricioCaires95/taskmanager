package br.com.spacer.taskmanager.integration;

import static br.com.spacer.taskmanager.utils.TestConstants.DEFAULT_USER_ID;
import static br.com.spacer.taskmanager.utils.TestDataCreator.newCreateUserDTO;
import static br.com.spacer.taskmanager.utils.TestDataCreator.newUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import br.com.spacer.taskmanager.api.facade.UserApi;
import br.com.spacer.taskmanager.core.BaseIntegrationTest;
import br.com.spacer.taskmanager.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;

class UserApiIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private UserApi api;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void setupEach() throws Exception {
        setLocalHostBasePath(api.getApiClient(), "/v1");
    }

    @Test
    void testCreateUserSuccess() {
        var createUserDto = newCreateUserDTO();

        var entitySaved = api.createUser(createUserDto);

        assertNotNull(entitySaved);
        assertNotNull(entitySaved.getId());
        assertNotNull(entitySaved.getName());
        assertNotNull(entitySaved.getEmail());
        assertEquals(createUserDto.getName(), entitySaved.getName());
        assertEquals(createUserDto.getEmail(), entitySaved.getEmail());
        assertEquals(createUserDto.getPassword(), entitySaved.getPassword());
    }

    @Test
    void testGetUserByIdSuccess() {
        var user = userRepository.saveAndFlush(newUser().build());

        var result = api.getUser(user.getId());

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(result.getPassword(), user.getPassword());
        assertEquals(result.getName(), user.getName());
        assertEquals(result.getEmail(), user.getEmail());
        assertEquals(result.getId(), user.getId());
    }

    @Test
    void testGetErrorWhenUserNotFound() {
        assertThrows(
            HttpClientErrorException.NotFound.class,
            () -> api.getUser(DEFAULT_USER_ID));
    }
//
//    @Test
//    void testUpdateTaskSuccess() {
//        var task = taskRepository.saveAndFlush(newTask().build());
//
//        var updateTaskDTO = newUpdateTaskDTO()
//                .description(task.getDescription().concat("-desc test"))
//                .title(task.getTitle().concat("-title test"))
//                .finishAt(task.getFinishAt().plusDays(3));
//
//        api.updateTask(task.getId(), updateTaskDTO);
//
//        var taskUpdated = taskRepository.findById(task.getId()).orElseThrow();
//
//        assertNotNull(taskUpdated);
//        assertEquals(taskUpdated.getTitle(), updateTaskDTO.getTitle());
//        assertEquals(taskUpdated.getDescription(), updateTaskDTO.getDescription());
//        assertEquals(taskUpdated.getFinishAt(), updateTaskDTO.getFinishAt());
//    }
//
//    @Test
//    void testUpdateTaskIdNotExist() {
//        assertThrows(
//                HttpClientErrorException.class,
//                () -> api.updateTask(DEFAULT_ID, newUpdateTaskDTO())
//        );
//    }
//
//    @Test
//    void testErrorWhenTitleDoesNotExist() {
//        var task = taskRepository.saveAndFlush(newTask().build());
//        assertThrows(
//                HttpClientErrorException.class,
//                () -> api.updateTask(task.getId(), newUpdateTaskDTO().title(StringUtils.EMPTY))
//        );
//    }
}
