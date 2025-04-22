// UsuarioService.java
package com.celiahelp.service;

import com.celiahelp.exception.NotFoundException;
import com.celiahelp.exception.ServiceException;
import com.celiahelp.model.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> getAll();
    Usuario getById(Long id) throws NotFoundException;
    Usuario create(Usuario usuario) throws ServiceException;
    Usuario update(Long id, Usuario usuario) throws ServiceException, NotFoundException;
    void delete(Long id) throws ServiceException, NotFoundException;
}
