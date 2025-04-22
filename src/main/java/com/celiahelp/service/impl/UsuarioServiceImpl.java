// UsuarioServiceImpl.java
package com.celiahelp.service.impl;

import com.celiahelp.exception.NotFoundException;
import com.celiahelp.exception.ServiceException;
import com.celiahelp.model.Usuario;
import com.celiahelp.repository.UsuarioRepository;
import com.celiahelp.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioServiceImpl(UsuarioRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Usuario> findAll() throws ServiceException {
        try {
            return repo.findAll();
        } catch (Exception e) {
            throw new ServiceException("Error al listar usuarios", e);
        }
    }

    @Override
    public Optional<Usuario> findById(Long id) throws ServiceException {
        try {
            return repo.findById(id);
        } catch (Exception e) {
            throw new ServiceException("Error al buscar usuario con id " + id, e);
        }
    }

    @Override
    public Usuario create(Usuario usuario) throws ServiceException {
        try {
            return repo.save(usuario);
        } catch (Exception e) {
            throw new ServiceException("Error al crear usuario", e);
        }
    }

    @Override
    public Usuario update(Long id, Usuario usuario)
            throws ServiceException, NotFoundException {
        try {
            return repo.findById(id)
                    .map(existing -> {
                        existing.setNombre(usuario.getNombre());
                        existing.setEmail(usuario.getEmail());
                        existing.setPasswordHash(usuario.getPasswordHash());
                        existing.setRol(usuario.getRol());
                        return repo.save(existing);
                    })
                    .orElseThrow(() ->
                            new NotFoundException("Usuario no encontrado con id " + id));
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar usuario con id " + id, e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            if (!repo.existsById(id)) {
                throw new NotFoundException("Usuario no encontrado con id " + id);
            }
            repo.deleteById(id);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar usuario con id " + id, e);
        }
    }
}
