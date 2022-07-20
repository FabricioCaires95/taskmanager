package br.com.spacer.taskmanager.validator;

import static br.com.spacer.taskmanager.validator.ValidationConstants.DESCRIPTION;
import static br.com.spacer.taskmanager.validator.ValidationConstants.FINISH_AT;
import static br.com.spacer.taskmanager.validator.ValidationConstants.MAX_LENGTH_DESCRIPTION;
import static br.com.spacer.taskmanager.validator.ValidationConstants.MAX_LENGTH_TITLE;
import static br.com.spacer.taskmanager.validator.ValidationConstants.TITLE;
import static br.com.spacer.taskmanager.validator.ValidationUtils.throwOnError;
import static br.com.spacer.taskmanager.validator.ValidationUtils.validateDateFuture;
import static br.com.spacer.taskmanager.validator.ValidationUtils.validateMaxLength;
import static br.com.spacer.taskmanager.validator.ValidationUtils.validateRequired;

import java.time.OffsetDateTime;
import org.springframework.stereotype.Component;
import br.com.spacer.taskmanager.api.model.CreateTaskDTO;
import br.com.spacer.taskmanager.api.model.UpdateTaskDTO;

@Component
public class TaskValidator {

    public void validate(CreateTaskDTO createTaskDTO) {
        var validationErrors = new ValidationErrors();

        validateDescription(createTaskDTO.getDescription(), validationErrors);
        validateTitle(createTaskDTO.getTitle(), validationErrors);
        validateDate(createTaskDTO.getFinishAt(), validationErrors);

        throwOnError(validationErrors);

    }

    public void validate(UpdateTaskDTO updateTaskDto) {
        var validationErrors = new ValidationErrors();

        validateDescription(updateTaskDto.getDescription(), validationErrors);
        validateTitle(updateTaskDto.getTitle(), validationErrors);
        validateDate(updateTaskDto.getFinishAt(), validationErrors);

        throwOnError(validationErrors);

    }

    private void validateDescription(String name, ValidationErrors validationErrors) {
        validateRequired(name, DESCRIPTION, validationErrors);
        validateMaxLength(name, DESCRIPTION, MAX_LENGTH_DESCRIPTION, validationErrors) ;
    }

    private void validateTitle(String title, ValidationErrors validationErrors) {
        validateRequired(title, TITLE, validationErrors);
        validateMaxLength(title, TITLE, MAX_LENGTH_TITLE, validationErrors);
    }

    private void validateDate(OffsetDateTime finishAt, ValidationErrors validationErrors) {
        if (validateRequired(finishAt, FINISH_AT, validationErrors)) {
            validateDateFuture(finishAt, validationErrors);
        }
    }

}
