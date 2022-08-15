package br.com.spacer.taskmanager.unit;

import static br.com.spacer.taskmanager.utils.TestDataCreator.newCreateUserDTO;
import static br.com.spacer.taskmanager.validator.ValidationConstants.EXCEEDS_MAX_LENGTH;
import static br.com.spacer.taskmanager.validator.ValidationConstants.INVALID_EMAIL;
import static br.com.spacer.taskmanager.validator.ValidationConstants.MAX_LENGTH_NAME;
import static br.com.spacer.taskmanager.validator.ValidationConstants.MISSING_FIELD;
import static br.com.spacer.taskmanager.validator.ValidationConstants.USER_EMAIL;
import static br.com.spacer.taskmanager.validator.ValidationConstants.USER_NAME;
import static org.apache.commons.lang3.StringUtils.rightPad;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.spacer.taskmanager.core.BaseUnitTest;
import br.com.spacer.taskmanager.exception.InvalidRequestException;
import br.com.spacer.taskmanager.validator.UserValidator;
import br.com.spacer.taskmanager.validator.ValidationError;
import br.com.spacer.taskmanager.validator.ValidationUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserValidatorTest extends BaseUnitTest {
    private UserValidator victim;

    @BeforeEach
    void setupEach() {
        victim = new UserValidator();
    }

    @Test
    void testValidateNameIsMissing() {
        var exception = assertThrows(
                InvalidRequestException.class,
                () -> victim.validate(newCreateUserDTO().name(null))
        );

        assertEquals(1, exception.getValidationErrors().getNumberErrors());
        assertEquals(
                new ValidationError(USER_NAME, USER_NAME + MISSING_FIELD),
                exception.getValidationErrors().getError(0)
        );
    }

    @Test
    void testValidateUserNameMaxLengthViolated() {
        var exception = assertThrows(
                InvalidRequestException.class,
                () -> victim.validate(newCreateUserDTO().name(rightPad("F", MAX_LENGTH_NAME + 1, "A")))
        );

        assertEquals(1, exception.getValidationErrors().getNumberErrors());
        assertEquals(
                new ValidationError(USER_NAME, USER_NAME + EXCEEDS_MAX_LENGTH),
                exception.getValidationErrors().getError(0)
        );
    }

    @Test
    void testValidateEmailIsMissing() {
        var exception = assertThrows(
                InvalidRequestException.class,
                () -> victim.validate(newCreateUserDTO().email(null))
        );

        assertEquals(1, exception.getValidationErrors().getNumberErrors());
        assertEquals(
                new ValidationError(USER_EMAIL, USER_EMAIL + MISSING_FIELD),
                exception.getValidationErrors().getError(0)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"joao#gmail.com", "test.123hotmail.com", "joaozinho-13outlook.com"})
    void testValidateEmailFormat(String email) {
        var exception = assertThrows(
                InvalidRequestException.class,
                () -> victim.validate(newCreateUserDTO().email(email))
        );

        assertEquals(1, exception.getValidationErrors().getNumberErrors());
        assertEquals(
                new ValidationError(USER_EMAIL, USER_EMAIL + INVALID_EMAIL),
                exception.getValidationErrors().getError(0)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"joao@gmail.com", "test.123@hotmail.com"})
    void testValidateEmailSuccessful(String email) {
        assertTrue(ValidationUtils.isEmailValid(email, null));
    }
}
