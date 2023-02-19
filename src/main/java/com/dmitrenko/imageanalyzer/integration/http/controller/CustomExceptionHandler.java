package com.dmitrenko.imageanalyzer.integration.http.controller;

import com.dmitrenko.imageanalyzer.model.dto.response.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handleNotFoundException(EntityNotFoundException e) {
        return new ErrorResponse()
            .setCode("404")
            .setMessage(e.getMessage());
    }
}
