package br.com.spacer.taskmanager.unit;


import static br.com.spacer.taskmanager.utils.MapperUtils.userMapper;
import static br.com.spacer.taskmanager.utils.TestDataCreator.newUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.spacer.taskmanager.core.BaseUnitTest;
import br.com.spacer.taskmanager.mapper.UserMapper;
import br.com.spacer.taskmanager.utils.TestDataCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserMapperUnitTest extends BaseUnitTest {
    private UserMapper victim;

    @BeforeEach
    void setUpEach() {
        victim = userMapper();
    }

    @Test
    void testFromEntityToDto() {
        var user = newUser().build();

        var userDto = victim.fromEntityToDto(user);

        assertNotNull(userDto);
        assertEquals(userDto.getName(), user.getName());
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getPassword(), user.getPassword());
    }

    @Test
    void testFromCreateTaskDtoToEntity() {
        var createUserDto = TestDataCreator.newCreateUserDTO();

        var user = victim.fromCreateDtoToEntity(createUserDto);

        assertNotNull(user);
        assertEquals(createUserDto.getName(), user.getName());
        assertEquals(createUserDto.getEmail(), user.getEmail());
        assertEquals(createUserDto.getPassword(), user.getPassword());
    }

    @Test
    void testMappingUpdateDtoToEntity() {
        var updateUserDto = TestDataCreator.newUpdateUserDTO();

        var user = victim.fromUpdateDtoToEntity(updateUserDto);

        assertNotNull(user);
        assertEquals(updateUserDto.getName(), user.getName());
        assertEquals(updateUserDto.getEmail(), user.getEmail());
        assertEquals(updateUserDto.getPassword(), user.getPassword());
    }
}
