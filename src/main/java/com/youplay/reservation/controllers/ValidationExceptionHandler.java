package com.youplay.reservation.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

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

        Throwable cause = ex.getCause();
        if (cause instanceof HttpMessageNotReadableException) {
            errorMessages.add("Ошибка десериализации: " + cause.getMessage());
        }

        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnknownExceptions(Exception ex) {
        if (ex instanceof HttpMessageNotReadableException) {
            return new ResponseEntity<>("Ошибка десериализации: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Неизвестная ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<String> handleBadRequestException(HttpClientErrorException.BadRequest e) {
        try {
             JsonNode jsonNode = new ObjectMapper().readTree(e.getResponseBodyAsString());
             if (jsonNode.get("errorCodeReadable").asText().equals("Overlap")) {
                 return ResponseEntity.badRequest().body("На данное время бронирование уже установлено");
             }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Произошла ошибка при обработке запроса");
        }
        return ResponseEntity.badRequest().body("Произошла ошибка при обработке запроса");
    }

}
