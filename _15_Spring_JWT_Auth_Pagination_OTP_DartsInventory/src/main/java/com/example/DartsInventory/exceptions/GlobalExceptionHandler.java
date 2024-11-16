package com.example.DartsInventory.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DartNotFoundException.class)
    public ProblemDetail handleDartNotFoundException(DartNotFoundException exc) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exc.getMessage());
    }

    @ExceptionHandler(FileExistsException.class)
    public ProblemDetail handleFileExistsException(FileExistsException exc) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exc.getMessage());
    }

    @ExceptionHandler(EmptyFileException.class)
    public ProblemDetail handleEmptyFileException(EmptyFileException exc) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exc.getMessage());
    }
}
