package br.com.spacer.taskmanager.validator;

import static br.com.spacer.taskmanager.validator.ValidationConstants.MAX_LENGTH_NAME;
import static br.com.spacer.taskmanager.validator.ValidationConstants.USER_EMAIL;
import static br.com.spacer.taskmanager.validator.ValidationConstants.USER_NAME;
import static br.com.spacer.taskmanager.validator.ValidationUtils.isEmailValid;
import static br.com.spacer.taskmanager.validator.ValidationUtils.throwOnError;
import static br.com.spacer.taskmanager.validator.ValidationUtils.validateMaxLength;
import static br.com.spacer.taskmanager.validator.ValidationUtils.validateMinValue;
import static br.com.spacer.taskmanager.validator.ValidationUtils.validateRequired;

import org.springframework.stereotype.Component;
import br.com.spacer.taskmanager.api.model.CreateUserDTO;
import br.com.spacer.taskmanager.api.model.UpdateUserDTO;

@Component
public class UserValidator {

    public void validate(CreateUserDTO createUserDTO) {
        var validationErrors = new ValidationErrors();

        validateName(createUserDTO.getName(), validationErrors);
        validateEmail(createUserDTO.getEmail(), validationErrors);
        validatePassword(createUserDTO.getPassword(), validationErrors);

        throwOnError(validationErrors);

    }

    public void validate(UpdateUserDTO updateTaskDto) {
        var validationErrors = new ValidationErrors();

        validateName(updateTaskDto.getName(), validationErrors);
        validateEmail(updateTaskDto.getEmail(), validationErrors);
        validatePassword(updateTaskDto.getPassword(), validationErrors);

        throwOnError(validationErrors);

    }

    private void validateName(String name, ValidationErrors validationErrors) {
        validateRequired(name, USER_NAME, validationErrors);
        validateMaxLength(name, USER_NAME, MAX_LENGTH_NAME, validationErrors) ;
    }

    private void validateEmail(String email, ValidationErrors validationErrors) {
        isEmailValid(email, validationErrors);
    }

    private void validatePassword(String password, ValidationErrors validationErrors) {
        //TODO
        validateMinValue(password.length(), USER_EMAIL, 6, validationErrors);
    }

}
