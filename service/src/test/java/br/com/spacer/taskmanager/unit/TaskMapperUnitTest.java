package br.com.spacer.taskmanager.unit;

import static br.com.spacer.taskmanager.utils.MapperUtils.taskMapper;
import static br.com.spacer.taskmanager.utils.TestDataCreator.newCreateTaskDTO;
import static br.com.spacer.taskmanager.utils.TestDataCreator.newTask;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import br.com.spacer.taskmanager.core.BaseUnitTest;
import br.com.spacer.taskmanager.mapper.TaskMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskMapperUnitTest extends BaseUnitTest {
    private TaskMapper victim;

    @BeforeEach
    void setUpEach() {
        victim = taskMapper();
    }

    @Test
    void testFromEntityToDto() {
        var entity = newTask().build();

        var dto = victim.fromEntityToDto(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getTitle(), dto.getTitle());
        assertEquals(entity.getDescription(), dto.getDescription());
        assertEquals(entity.getFinishAt(), dto.getFinishAt());
        assertEquals(entity.getCreatedAt(), dto.getCreatedAt());
        assertFalse(dto.getIsFinished());
    }

    @Test
    void testFromCreateTaskDtoToEntity() {
        var createDto = newCreateTaskDTO();

        var entity = victim.fromCreateDtoToEntity(createDto);

        assertEquals(entity.getTitle(), createDto.getTitle());
        assertEquals(entity.getDescription(), createDto.getDescription());
        assertEquals(entity.getFinishAt(), createDto.getFinishAt());
    }

}
