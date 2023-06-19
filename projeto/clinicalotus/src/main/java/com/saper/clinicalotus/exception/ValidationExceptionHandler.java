package com.saper.clinicalotus.exception;

import com.saper.clinicalotus.dto.ErrorDTO;
import com.saper.clinicalotus.exception.exceptions.ConflictStoreException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request){
        List<ErrorDTO> errors = new ArrayList<>();
        exception
                .getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    errors.add(new ErrorDTO(
                            Instant.now(),
                            HttpStatus.BAD_REQUEST.toString(),
                            ((FieldError) error).getField(),
                            error.getDefaultMessage(),
                            request.getRequestURI()));
                });
        return errors;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public Object handleNoSuchMethodException(NoSuchElementException exception, HttpServletRequest request) {
        return new ErrorDTO( Instant.now(),
                HttpStatus.NOT_FOUND.toString(),
                "resource not found",
                exception.getMessage(),
                request.getRequestURI()
        );
    }

    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Object handleDataIntegrityViolationException(DataIntegrityViolationException exception, HttpServletRequest request) {

        return new ErrorDTO( Instant.now(),
                HttpStatus.CONFLICT.toString(),
                "database conflict",
                "Could not execute statement",
                request.getRequestURI()
        );
    }

    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(ConflictStoreException.class)
    public Object handleConflictStoreException(ConflictStoreException exception, HttpServletRequest request) {

        return new ErrorDTO( Instant.now(),
                HttpStatus.CONFLICT.toString(),
                "database conflict",
                exception.getMessage(),
                request.getRequestURI()
        );
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception exception, HttpServletRequest request) {

        return new ErrorDTO( Instant.now(),
                HttpStatus.BAD_REQUEST.toString(),
                "unknown error",
                exception.getMessage(),
                request.getRequestURI()
        );
    }
}
