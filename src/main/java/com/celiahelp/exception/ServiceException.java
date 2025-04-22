package com.celiahelp.exception;

/**
 * Excepción genérica de servicio, mapea a HTTP 500.
 */
public class ServiceException extends Exception {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}