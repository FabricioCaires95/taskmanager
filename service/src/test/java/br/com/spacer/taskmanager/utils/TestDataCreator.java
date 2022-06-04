package br.com.spacer.taskmanager.utils;

import static br.com.spacer.taskmanager.utils.TestConstants.CREATED_AT;
import static br.com.spacer.taskmanager.utils.TestConstants.DEFAULT_DESCRIPTION;
import static br.com.spacer.taskmanager.utils.TestConstants.DEFAULT_FINISH;
import static br.com.spacer.taskmanager.utils.TestConstants.DEFAULT_ID;
import static br.com.spacer.taskmanager.utils.TestConstants.DEFAULT_TITLE;
import static br.com.spacer.taskmanager.utils.TestConstants.IS_FINISHED;

import br.com.spacer.taskmanager.api.model.CreateTaskDTO;
import br.com.spacer.taskmanager.domain.entity.Task;

public final class TestDataCreator {

    private TestDataCreator() {}

    public static Task.Builder newTask() {
        return Task.newBuilder()
                .id(DEFAULT_ID)
                .title(DEFAULT_TITLE)
                .description(DEFAULT_DESCRIPTION)
                .finishAt(DEFAULT_FINISH)
                .isFinished(IS_FINISHED)
                .createdAt(CREATED_AT);
    }

    public static CreateTaskDTO newCreateTaskDTO() {
        return new CreateTaskDTO().title(DEFAULT_TITLE).description(DEFAULT_DESCRIPTION).finishAt(DEFAULT_FINISH);
    }
}
