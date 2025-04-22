// == Interfaz de servicio para Rol ==
package com.celiahelp.service;

import com.celiahelp.exception.NotFoundException;
import com.celiahelp.exception.ServiceException;
import com.celiahelp.model.Rol;

import java.util.List;

public interface RolService {
    List<Rol> getAll();
    Rol getById(Long id) throws NotFoundException;
    Rol create(Rol rol) throws ServiceException;
    Rol update(Long id, Rol rol) throws ServiceException, NotFoundException;
    void delete(Long id) throws ServiceException, NotFoundException;
}
