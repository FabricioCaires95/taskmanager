package br.com.spacer.taskmanager.utils;

import static br.com.spacer.taskmanager.utils.TestConstants.CREATED_AT;
import static br.com.spacer.taskmanager.utils.TestConstants.DEFAULT_DESCRIPTION;
import static br.com.spacer.taskmanager.utils.TestConstants.DEFAULT_FINISH;
import static br.com.spacer.taskmanager.utils.TestConstants.DEFAULT_ID;
import static br.com.spacer.taskmanager.utils.TestConstants.DEFAULT_TITLE;
import static br.com.spacer.taskmanager.utils.TestConstants.DEFAULT_USER_EMAIL;
import static br.com.spacer.taskmanager.utils.TestConstants.DEFAULT_USER_ID;
import static br.com.spacer.taskmanager.utils.TestConstants.DEFAULT_USER_NAME;
import static br.com.spacer.taskmanager.utils.TestConstants.DEFAULT_USER_PASSWORD;
import static br.com.spacer.taskmanager.utils.TestConstants.IS_FINISHED;

import br.com.spacer.taskmanager.api.model.CreateTaskDTO;
import br.com.spacer.taskmanager.api.model.CreateUserDTO;
import br.com.spacer.taskmanager.api.model.UpdateTaskDTO;
import br.com.spacer.taskmanager.api.model.UpdateUserDTO;
import br.com.spacer.taskmanager.domain.entity.Task;
import br.com.spacer.taskmanager.domain.entity.User;

public final class TestDataCreator {

    private TestDataCreator() {}

    public static Task.Builder newTask() {
        return Task.newBuilder()
                .id(DEFAULT_ID)
                .title(DEFAULT_TITLE)
                .description(DEFAULT_DESCRIPTION)
                .finishAt(DEFAULT_FINISH)
                .user(User.newUser()
                        .id(DEFAULT_ID)
                        .name(DEFAULT_USER_NAME)
                        .email(DEFAULT_USER_EMAIL)
                        .password(DEFAULT_USER_PASSWORD)
                        .createdAt(CREATED_AT).build())
                .isFinished(IS_FINISHED)
                .createdAt(CREATED_AT);
    }

    public static CreateTaskDTO newCreateTaskDTO() {
        return new CreateTaskDTO().title(DEFAULT_TITLE).description(DEFAULT_DESCRIPTION).finishAt(DEFAULT_FINISH).userId(DEFAULT_USER_ID);
    }

    public static UpdateTaskDTO newUpdateTaskDTO() {
        return new UpdateTaskDTO().title(DEFAULT_TITLE).description(DEFAULT_DESCRIPTION).finishAt(DEFAULT_FINISH);
    }

    public static User.Builder newUser() {
        return User.newUser()
            .id(DEFAULT_USER_ID)
            .name(DEFAULT_USER_NAME)
            .email(DEFAULT_USER_EMAIL)
            .password(DEFAULT_USER_PASSWORD)
            .createdAt(CREATED_AT);
    }

    public static CreateUserDTO newCreateUserDTO() {
        return new CreateUserDTO().name(DEFAULT_USER_NAME).email(DEFAULT_USER_EMAIL).password(DEFAULT_USER_PASSWORD);
    }

    public static UpdateUserDTO newUpdateUserDTO() {
        return new UpdateUserDTO().name(DEFAULT_USER_NAME).email(DEFAULT_USER_EMAIL).password(DEFAULT_USER_PASSWORD);
    }
}
