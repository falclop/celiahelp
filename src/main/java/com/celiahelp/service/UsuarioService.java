// UsuarioService.java
package com.celiahelp.service;

import com.celiahelp.exception.NotFoundException;
import com.celiahelp.exception.ServiceException;
import com.celiahelp.model.Usuario;

import java.util.List;
import java.util.Optional;

/*
 * Operaciones de negocio para Usuario.
 */
public interface UsuarioService {

    List<Usuario> findAll() throws ServiceException;

    Optional<Usuario> findById(Long id) throws ServiceException;

    Usuario create(Usuario usuario) throws ServiceException;

    Usuario update(Long id, Usuario usuario)
            throws ServiceException, NotFoundException;

    void delete(Long id) throws ServiceException;
}
