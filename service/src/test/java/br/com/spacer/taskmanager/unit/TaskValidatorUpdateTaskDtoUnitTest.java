package br.com.spacer.taskmanager.unit;

import static br.com.spacer.taskmanager.utils.DateUtils.now;
import static br.com.spacer.taskmanager.utils.TestDataCreator.newUpdateTaskDTO;
import static br.com.spacer.taskmanager.validator.ValidationConstants.DESCRIPTION;
import static br.com.spacer.taskmanager.validator.ValidationConstants.EXCEEDS_MAX_LENGTH;
import static br.com.spacer.taskmanager.validator.ValidationConstants.FINISH_AT;
import static br.com.spacer.taskmanager.validator.ValidationConstants.IN_THE_PAST;
import static br.com.spacer.taskmanager.validator.ValidationConstants.MAX_LENGTH_DESCRIPTION;
import static br.com.spacer.taskmanager.validator.ValidationConstants.MAX_LENGTH_TITLE;
import static br.com.spacer.taskmanager.validator.ValidationConstants.MISSING_FIELD;
import static br.com.spacer.taskmanager.validator.ValidationConstants.TITLE;
import static org.apache.commons.lang3.StringUtils.rightPad;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.spacer.taskmanager.core.BaseUnitTest;
import br.com.spacer.taskmanager.exception.InvalidRequestException;
import br.com.spacer.taskmanager.validator.TaskValidator;
import br.com.spacer.taskmanager.validator.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskValidatorUpdateTaskDtoUnitTest extends BaseUnitTest {

    private TaskValidator victim;

    @BeforeEach
    void setupEach() {
        victim = new TaskValidator();
    }

    @Test
    void testValidateTaskDescriptionIsMissing() {
        var exception = assertThrows(
                InvalidRequestException.class,
                () -> victim.validate(newUpdateTaskDTO().description(null))
        );

        assertEquals(1, exception.getValidationErrors().getNumberErrors());
        assertEquals(
            new ValidationError(DESCRIPTION, DESCRIPTION + MISSING_FIELD),
            exception.getValidationErrors().getError(0)
        );
    }

    @Test
    void testValidateTaskDescriptionMaxLengthIsViolated() {
        var exception = assertThrows(
                InvalidRequestException.class,
                () -> victim.validate(newUpdateTaskDTO().description(rightPad("F", MAX_LENGTH_DESCRIPTION + 1, "A")))
        );

        assertEquals(1, exception.getValidationErrors().getNumberErrors());
        assertEquals(
                new ValidationError(DESCRIPTION, DESCRIPTION + EXCEEDS_MAX_LENGTH),
                exception.getValidationErrors().getError(0)
        );
    }

    @Test
    void testValidateTaskTitleFieldIsMissing() {
        var exception = assertThrows(
                InvalidRequestException.class,
                () -> victim.validate(newUpdateTaskDTO().title(null))
        );

        assertEquals(1, exception.getValidationErrors().getNumberErrors());
        assertEquals(
                new ValidationError(TITLE, TITLE + MISSING_FIELD),
                exception.getValidationErrors().getError(0)
        );
    }

    @Test
    void testValidateTaskTitleMaxLengthIsViolated() {
        var exception = assertThrows(
                InvalidRequestException.class,
                () -> victim.validate(newUpdateTaskDTO().title(rightPad("F", MAX_LENGTH_TITLE + 1, "A")))
        );

        assertEquals(1, exception.getValidationErrors().getNumberErrors());
        assertEquals(
                new ValidationError(TITLE, TITLE + EXCEEDS_MAX_LENGTH),
                exception.getValidationErrors().getError(0)
        );
    }

    @Test
    void testValidateTaskFinishAtFieldIsMissing() {
        var exception = assertThrows(
                InvalidRequestException.class,
                () -> victim.validate(newUpdateTaskDTO().finishAt(null))
        );

        assertEquals(1, exception.getValidationErrors().getNumberErrors());
        assertEquals(
                new ValidationError(FINISH_AT, FINISH_AT + MISSING_FIELD),
                exception.getValidationErrors().getError(0)
        );
    }

    @Test
    void testValidateTaskFinishAtIsInThePast() {
        var exception = assertThrows(
                InvalidRequestException.class,
                () -> victim.validate(newUpdateTaskDTO().finishAt(now().minusDays(2)))
        );

        assertEquals(1, exception.getValidationErrors().getNumberErrors());
        assertEquals(
                new ValidationError(FINISH_AT, FINISH_AT + IN_THE_PAST),
                exception.getValidationErrors().getError(0)
        );
    }
}
