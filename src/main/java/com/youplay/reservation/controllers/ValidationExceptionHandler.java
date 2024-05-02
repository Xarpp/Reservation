package com.youplay.reservation.controllers;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> handleValidationExceptions(ConstraintViolationException ex) {
        List<String> errorMessages = new ArrayList<>();

        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errorMessages.add(violation.getMessage());
        }

        // Добавляем сообщение об ошибке десериализации, если оно есть
        Throwable cause = ex.getCause();
        if (cause instanceof HttpMessageNotReadableException) {
            errorMessages.add("Ошибка десериализации: " + cause.getMessage());
        }

        // Возвращаем список сообщений об ошибках
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnknownExceptions(Exception ex) {
        // Проверяем, является ли исключение ошибкой десериализации
        if (ex instanceof HttpMessageNotReadableException) {
            return new ResponseEntity<>("Ошибка десериализации: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        // Возвращаем сообщение об неизвестной ошибке
        return new ResponseEntity<>("Неизвестная ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
