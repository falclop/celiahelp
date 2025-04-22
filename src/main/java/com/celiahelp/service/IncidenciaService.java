// IncidenciaService.java
package com.celiahelp.service;

import com.celiahelp.exception.ServiceException;
import com.celiahelp.exception.NotFoundException;
import com.celiahelp.model.Incidencia;

import java.util.List;
import java.util.Optional;

public interface IncidenciaService {
    List<Incidencia> findAll() throws ServiceException;
    Optional<Incidencia> findById(Long id) throws ServiceException;
    Incidencia create(Incidencia incidencia) throws ServiceException;
    Incidencia update(Long id, Incidencia incidencia) throws ServiceException, NotFoundException;
    void delete(Long id) throws ServiceException;
}
