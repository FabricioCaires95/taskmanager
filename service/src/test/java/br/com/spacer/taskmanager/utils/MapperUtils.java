package br.com.spacer.taskmanager.utils;

import br.com.spacer.taskmanager.mapper.TaskMapper;
import br.com.spacer.taskmanager.mapper.UserMapper;
import org.mapstruct.factory.Mappers;

public final class MapperUtils {

    private MapperUtils() {}

    public static TaskMapper taskMapper() {
        return Mappers.getMapper(TaskMapper.class);
    }

    public static UserMapper userMapper() {
        return Mappers.getMapper(UserMapper.class);
    }
}
