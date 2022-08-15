package br.com.spacer.taskmanager.unit;

import static br.com.spacer.taskmanager.utils.MapperUtils.userMapper;
import static br.com.spacer.taskmanager.utils.TestDataCreator.newCreateUserDTO;
import static br.com.spacer.taskmanager.utils.TestDataCreator.newUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.spacer.taskmanager.core.BaseUnitTest;
import br.com.spacer.taskmanager.domain.repository.UserRepository;
import br.com.spacer.taskmanager.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class UserServiceUnitTest extends BaseUnitTest {
    private UserService victim;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void beforeEach() {
        victim = new UserService(userRepository, userMapper());
    }

    @Test
    void testCreateTaskSuccess() {
        var createUserDTO = newCreateUserDTO();
        when(userRepository.save(any())).thenReturn(newUser().build());

        var userDto = victim.createUser(createUserDTO);

        assertEquals(userDto.getEmail(), createUserDTO.getEmail());
        assertEquals(userDto.getName(), createUserDTO.getName());
        assertEquals(userDto.getPassword(), createUserDTO.getPassword());

        verify(userRepository).save(any());
    }
}
