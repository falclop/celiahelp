package com.celiahelp.exception;

/**
 * Excepción para recurso no encontrado, mapea a HTTP 404.
 */
public class NotFoundException extends ServiceException {

    public NotFoundException(String message) {
        super(message);
    }
}