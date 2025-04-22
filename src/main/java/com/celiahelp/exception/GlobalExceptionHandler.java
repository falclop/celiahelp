package com.celiahelp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controlador de consejos global para mapear excepciones a códigos HTTP.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Void> handleNotFound(NotFoundException ex) {
        // Devuelve 404 cuando el recurso no existe
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Void> handleServiceError(ServiceException ex) {
        // Devuelve 500 para errores internos de servicio
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleUnexpected(Exception ex) {
        // Captura cualquier otra excepción imprevista
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}