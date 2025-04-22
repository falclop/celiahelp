// UsuarioServiceImpl.java
package com.celiahelp.service.impl;

import com.celiahelp.exception.NotFoundException;
import com.celiahelp.exception.ServiceException;
import com.celiahelp.model.Usuario;
import com.celiahelp.repository.UsuarioRepository;
import com.celiahelp.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario getById(Long id) throws NotFoundException {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado con id: " + id));
    }

    @Override
    public Usuario create(Usuario usuario) throws ServiceException {
        try {
            return usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new ServiceException("Error al crear el usuario", e);
        }
    }

    @Override
    public Usuario update(Long id, Usuario usuario) throws ServiceException, NotFoundException {
        Usuario existente = getById(id);
        usuario.setId(existente.getId());
        return usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Long id) throws ServiceException, NotFoundException {
        Usuario usuario = getById(id);
        try {
            usuarioRepository.delete(usuario);
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar el usuario", e);
        }
    }
}
