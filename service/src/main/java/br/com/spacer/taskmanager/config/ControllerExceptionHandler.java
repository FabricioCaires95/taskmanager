package br.com.spacer.taskmanager.config;

import static br.com.spacer.taskmanager.utils.ResponseEntityUtils.notFound;
import static java.util.stream.Collectors.toList;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import br.com.spacer.taskmanager.api.model.ResponseError;
import br.com.spacer.taskmanager.exception.InvalidRequestException;
import br.com.spacer.taskmanager.exception.TaskNotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({TaskNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(Exception exception) {
        return notFound();
    }

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public List<ResponseError> handleInvalidRequestException(InvalidRequestException exception) {
        return exception
                .getValidationErrors()
                .stream()
                .map(e -> new ResponseError().field(e.getField()).errorCode(e.getErrorCode()))
                .collect(toList());
    }
}
