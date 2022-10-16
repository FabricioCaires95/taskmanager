package br.com.spacer.taskmanager.integration;

import static br.com.spacer.taskmanager.utils.TestConstants.DEFAULT_USER_EMAIL;
import static br.com.spacer.taskmanager.utils.TestConstants.DEFAULT_USER_ID;
import static br.com.spacer.taskmanager.utils.TestConstants.DEFAULT_USER_NAME;
import static br.com.spacer.taskmanager.utils.TestConstants.DEFAULT_USER_PASSWORD;
import static br.com.spacer.taskmanager.utils.TestDataCreator.newCreateUserDTO;
import static br.com.spacer.taskmanager.utils.TestDataCreator.newUpdateUserDTO;
import static br.com.spacer.taskmanager.utils.TestDataCreator.newUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import br.com.spacer.taskmanager.api.facade.UserApi;
import br.com.spacer.taskmanager.core.BaseIntegrationTest;
import br.com.spacer.taskmanager.domain.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    void resetData() {
        userRepository.deleteAll();
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

    @Test
    void testUpdateTaskSuccess() {
        var user = userRepository.saveAndFlush(newUser().build());

        var updateUserDto = newUpdateUserDTO()
                    .email(DEFAULT_USER_EMAIL)
                    .password(DEFAULT_USER_PASSWORD)
                    .name(DEFAULT_USER_NAME);

        api.updateUser(user.getId(), updateUserDto);

        var userUpdated = userRepository.findById(user.getId()).orElseThrow();

        assertNotNull(userUpdated);
        assertEquals(userUpdated.getName(), updateUserDto.getName());
        assertEquals(userUpdated.getPassword(), updateUserDto.getPassword());
        assertEquals(userUpdated.getEmail(), updateUserDto.getEmail());
    }

    @Test
    void testUpdateUserIdNotExist() {
        assertThrows(
                HttpClientErrorException.class,
                () -> api.updateUser(DEFAULT_USER_ID, newUpdateUserDTO())
        );
    }

    @Test
    void testErrorWhenPasswordIsIncorrect() {
        var user = userRepository.saveAndFlush(newUser().build());
        assertThrows(
                HttpClientErrorException.class,
                () -> api.updateUser(user.getId(), newUpdateUserDTO().password("abc1234"))
        );
    }
}
