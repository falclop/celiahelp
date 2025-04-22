// LogService.java
package com.celiahelp.service;

import com.celiahelp.exception.ServiceException;
import com.celiahelp.model.Log;
import com.celiahelp.model.Incidencia;
import com.celiahelp.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface LogService {
    List<Log> findAll() throws ServiceException;
    Optional<Log> findById(Long id) throws ServiceException;
    Log create(Log log) throws ServiceException;
    void delete(Long id) throws ServiceException;

    // Métodos adicionales que requería el controlador
    List<Log> findByIncidencia(Incidencia incidencia) throws ServiceException;
    List<Log> findByUsuario(Usuario usuario) throws ServiceException;
}
