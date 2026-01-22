package com.planify.domain.controller;

import com.planify.domain.dto.ErrorDto;
import com.planify.domain.exception.TaskNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;

@ControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<ErrorDto> handleValidationException(MethodArgumentNotValidException ex)
    {
        String errorMessage=ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("Validation Field");

        return new ResponseEntity<>(new ErrorDto(errorMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorDto> handleTaskNotFoundException(TaskNotFoundException ex)
    {
        UUID taskNotFoundId=ex.getID();
        String message=String.format("Task with %s not found",taskNotFoundId);
        return new ResponseEntity<>(new ErrorDto(message),HttpStatus.BAD_REQUEST);
    }
}
