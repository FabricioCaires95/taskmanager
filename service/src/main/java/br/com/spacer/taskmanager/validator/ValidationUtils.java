package br.com.spacer.taskmanager.validator;

import static br.com.spacer.taskmanager.utils.DateUtils.now;
import static br.com.spacer.taskmanager.validator.ValidationConstants.BELLOW_MIN_VALUE;
import static br.com.spacer.taskmanager.validator.ValidationConstants.EXCEEDS_MAX_LENGTH;
import static br.com.spacer.taskmanager.validator.ValidationConstants.EXCEEDS_MAX_VALUE;
import static br.com.spacer.taskmanager.validator.ValidationConstants.FINISH_AT;
import static br.com.spacer.taskmanager.validator.ValidationConstants.INVALID_EMAIL;
import static br.com.spacer.taskmanager.validator.ValidationConstants.INVALID_PASSWORD;
import static br.com.spacer.taskmanager.validator.ValidationConstants.IN_THE_PAST;
import static br.com.spacer.taskmanager.validator.ValidationConstants.MISSING_FIELD;
import static br.com.spacer.taskmanager.validator.ValidationConstants.USER_EMAIL;
import static br.com.spacer.taskmanager.validator.ValidationConstants.USER_PASSWORD;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.time.OffsetDateTime;
import java.util.regex.Pattern;
import br.com.spacer.taskmanager.exception.InvalidRequestException;

public final class ValidationUtils {

    private static final String EMAIL_PATTERN = "^(.+)@(\\S+)$";
    private static final String PASSWORD_PATTERN = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,}$";

    private ValidationUtils() {}

    public static void throwOnError(ValidationErrors errors) {
        if (errors.hasErrors()) {
            throw new InvalidRequestException(errors);
        }
    }

    public static boolean validateRequired(String field, String fieldName, ValidationErrors validationErrors) {
        if (isBlank(field)) {
            validationErrors.add(fieldName, fieldName + MISSING_FIELD);
            return false;
        }
        return true;
    }

    public static boolean validateRequired(Object field, String fieldName, ValidationErrors validationErrors) {
        if (isNull(field)) {
            validationErrors.add(fieldName, fieldName + MISSING_FIELD);
            return false;
        }
        return true;
    }

    public static boolean validateMaxLength(String field, String fieldName, int maxLength, ValidationErrors validationErrors) {
        if (!isBlank(field) && field.trim().length() > maxLength) {
            validationErrors.add(fieldName, fieldName + EXCEEDS_MAX_LENGTH);
            return false;
        }
        return true;
    }

    public static boolean validateDateFuture(OffsetDateTime finishAt, ValidationErrors validationErrors) {
        if (finishAt.isBefore(now())) {
            validationErrors.add(FINISH_AT, FINISH_AT + IN_THE_PAST);
        }

        return false;
    }

    public static boolean validateMaxValue(
            Integer field,
            String fieldName,
            int maxValue,
            ValidationErrors validationErrors
    ) {
        if (!isNull(field) && field > maxValue) {
            validationErrors.add(fieldName, fieldName + EXCEEDS_MAX_VALUE);
            return false;
        }
        return true;
    }

    public static boolean validateMinValue(
            Integer field,
            String fieldName,
            int minValue,
            ValidationErrors validationErrors
    ) {
        if (!isNull(field) && field < minValue) {
            validationErrors.add(fieldName, fieldName + BELLOW_MIN_VALUE);
            return false;
        }
        return true;
    }

    public static boolean isEmailValid(String email, ValidationErrors validationErrors) {
        if (!validateRequired(email, USER_EMAIL, validationErrors)) {
            return false;
        }
        if (!Pattern.compile(EMAIL_PATTERN).matcher(email).matches()) {
            validationErrors.add(USER_EMAIL, USER_EMAIL + INVALID_EMAIL);
            return false;
        }
        return true;
    }

    public static boolean isPasswordValid(String password, ValidationErrors validationErrors) {
        if (!Pattern.compile(PASSWORD_PATTERN).matcher(password).matches()) {
            validationErrors.add(USER_PASSWORD, USER_PASSWORD + INVALID_PASSWORD);
            return false;
        }

        return true;
    }
}
