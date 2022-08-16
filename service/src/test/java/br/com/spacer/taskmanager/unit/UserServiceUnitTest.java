package br.com.spacer.taskmanager.unit;

import static br.com.spacer.taskmanager.utils.MapperUtils.userMapper;
import static br.com.spacer.taskmanager.utils.TestConstants.DEFAULT_ID;
import static br.com.spacer.taskmanager.utils.TestConstants.DEFAULT_USER_ID;
import static br.com.spacer.taskmanager.utils.TestDataCreator.newCreateUserDTO;
import static br.com.spacer.taskmanager.utils.TestDataCreator.newUser;
import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import br.com.spacer.taskmanager.core.BaseUnitTest;
import br.com.spacer.taskmanager.domain.repository.UserRepository;
import br.com.spacer.taskmanager.exception.UserNotFoundException;
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
    void testCreateUserSuccess() {
        var createUserDTO = newCreateUserDTO();
        when(userRepository.save(any())).thenReturn(newUser().build());

        var userDto = victim.createUser(createUserDTO);

        assertEquals(userDto.getEmail(), createUserDTO.getEmail());
        assertEquals(userDto.getName(), createUserDTO.getName());
        assertEquals(userDto.getPassword(), createUserDTO.getPassword());

        verify(userRepository).save(any());
    }

    @Test
    void testGetUserByIdSuccess() {
        var user = newUser().build();
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        var userDto = victim.getUserById(DEFAULT_USER_ID);

        assertNotNull(userDto);
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getName(), user.getName());
        assertEquals(userDto.getPassword(), user.getPassword());

        verify(userRepository).findById(any());
    }

    @Test
    void testGetUserByIdNotFound() {
        when(userRepository.findById(any())).thenReturn(empty());;
        assertThrows(UserNotFoundException.class, () -> victim.getUserById(DEFAULT_ID));
    }

    @Test
    void testValidateExceptionMessageWhenUserIsNotFound() {
        when(userRepository.findById(any())).thenReturn(empty());;
        assertThatThrownBy(() ->
                victim.getUserById(DEFAULT_ID))
                .hasMessage("User not found ")
                .isInstanceOf(UserNotFoundException.class);
    }
}
