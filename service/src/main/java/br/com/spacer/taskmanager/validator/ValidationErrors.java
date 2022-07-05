package br.com.spacer.taskmanager.validator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.data.util.Streamable;

public class ValidationErrors implements Streamable<ValidationError> {
    private final List<ValidationError> validationErrors;

    public ValidationErrors() {
        this.validationErrors = new ArrayList<>();
    }

    public ValidationErrors add(String field, String errorCode) {
        return add(new ValidationError(field, errorCode));
    }

    public ValidationErrors add(ValidationError validationError) {
        validationErrors.add(validationError);
        return this;
    }

    public ValidationError getError(int index) {
        return validationErrors.get(index);
    }

    public int getNumberErrors() {
        return validationErrors.size();
    }

    public boolean hasErrors() {
        return !validationErrors.isEmpty();
    }

    @Override
    public Iterator<ValidationError> iterator() {
        return validationErrors.iterator();
    }

    @Override
    public String toString() {
        return "ValidationErrors{" +
                "validationErrors=" + validationErrors +
                '}';
    }
}
