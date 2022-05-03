package com.example.mbticodingtask.api.errorhandling;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class IntervalControllerAdvice {

    private static final String SEPARATOR = "; ";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Fault handleMethodArgumentNotValid(@NonNull final MethodArgumentNotValidException exception) {
        final StringBuilder message = new StringBuilder();
        final List<String> errors = new ArrayList<>();
        exception.getBindingResult()
                 .getAllErrors()
                 .forEach(error -> {
                     message.append(error.getDefaultMessage()).append(SEPARATOR);
                     if (error instanceof FieldError) {
                         errors.add(((FieldError) error).getField());
                     }
                 });

        return new Fault(message.toString(), errors);
    }
}
