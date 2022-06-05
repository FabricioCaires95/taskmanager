package br.com.spacer.taskmanager.config;

import static br.com.spacer.taskmanager.utils.ResponseEntityUtils.notFound;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import br.com.spacer.taskmanager.exception.TaskNotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({TaskNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(Exception exception) {
        return notFound();
    }
}
