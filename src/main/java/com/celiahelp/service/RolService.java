// == Interfaz de servicio para Rol ==
package com.celiahelp.service;

import com.celiahelp.exception.ServiceException;
import com.celiahelp.model.Rol;
import java.util.List;
import java.util.Optional;

public interface RolService {

    List<Rol> findAll() throws ServiceException;

    Optional<Rol> findById(Long id) throws ServiceException;

    Rol create(Rol rol) throws ServiceException;

    Rol update(Long id, Rol rol) throws ServiceException;

    void delete(Long id) throws ServiceException;

    Optional<Rol> findByTipo(Rol.Tipo tipo) throws ServiceException;
}