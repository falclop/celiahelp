// IncidenciaService.java
package com.celiahelp.service;

import com.celiahelp.exception.NotFoundException;
import com.celiahelp.exception.ServiceException;
import com.celiahelp.model.Incidencia;

import java.util.List;

public interface IncidenciaService {
    List<Incidencia> getAll();
    Incidencia getById(Long id) throws NotFoundException;
    Incidencia create(Incidencia incidencia) throws ServiceException;
    Incidencia update(Long id, Incidencia incidencia) throws ServiceException, NotFoundException;
    void delete(Long id) throws ServiceException, NotFoundException;
}
