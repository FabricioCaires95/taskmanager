package br.com.spacer.taskmanager.exception;

import br.com.spacer.taskmanager.validator.ValidationError;
import br.com.spacer.taskmanager.validator.ValidationErrors;

public class InvalidRequestException extends RuntimeException {
    private final ValidationErrors validationErrors;

    public InvalidRequestException(ValidationErrors validationErrors) {
        super(validationErrors.toString());
        this.validationErrors = validationErrors;
    }

    public InvalidRequestException(ValidationError validationError) {
        this(new ValidationErrors().add(validationError));
    }

    public ValidationErrors getValidationErrors() {
        return validationErrors;
    }
}
