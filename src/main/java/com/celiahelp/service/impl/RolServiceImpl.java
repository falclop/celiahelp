// == Implementación de RolService ==
package com.celiahelp.service.impl;

import com.celiahelp.exception.NotFoundException;
import com.celiahelp.exception.ServiceException;
import com.celiahelp.model.Rol;
import com.celiahelp.repository.RolRepository;
import com.celiahelp.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    @Override
    public List<Rol> getAll() {
        return rolRepository.findAll();
    }

    @Override
    public Rol getById(Long id) throws NotFoundException {
        return rolRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Rol no encontrado con id: " + id));
    }

    @Override
    public Rol create(Rol rol) throws ServiceException {
        try {
            return rolRepository.save(rol);
        } catch (Exception e) {
            throw new ServiceException("Error al crear el rol", e);
        }
    }

    @Override
    public Rol update(Long id, Rol rol) throws ServiceException, NotFoundException {
        Rol existente = getById(id);
        rol.setId(existente.getId());
        return rolRepository.save(rol);
    }

    @Override
    public void delete(Long id) throws ServiceException, NotFoundException {
        Rol rol = getById(id);
        try {
            rolRepository.delete(rol);
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar el rol", e);
        }
    }
}
