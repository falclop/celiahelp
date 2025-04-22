// == Implementación de RolService ==
package com.celiahelp.service.impl;

import com.celiahelp.exception.NotFoundException;
import com.celiahelp.exception.ServiceException;
import com.celiahelp.model.Rol;
import com.celiahelp.repository.RolRepository;
import com.celiahelp.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepo;

    @Autowired
    public RolServiceImpl(RolRepository rolRepo) {
        this.rolRepo = rolRepo;
    }

    @Override
    public List<Rol> findAll() throws ServiceException {
        try {
            return rolRepo.findAll();
        } catch (DataAccessException ex) {
            throw new ServiceException("Error al listar roles", ex);
        }
    }

    @Override
    public Optional<Rol> findById(Long id) throws ServiceException {
        try {
            return rolRepo.findById(id);
        } catch (DataAccessException ex) {
            throw new ServiceException("Error al buscar rol por id " + id, ex);
        }
    }

    @Override
    public Rol create(Rol rol) throws ServiceException {
        try {
            return rolRepo.save(rol);
        } catch (DataAccessException ex) {
            throw new ServiceException("Error al crear rol", ex);
        }
    }

    @Override
    public Rol update(Long id, Rol rol) throws ServiceException {
        try {
            return rolRepo.findById(id)
                    .map(existing -> {
                        existing.setTipo(rol.getTipo());
                        return rolRepo.save(existing);
                    })
                    .orElseThrow(() -> new NotFoundException("Rol no encontrado con id " + id));
        } catch (DataAccessException ex) {
            throw new ServiceException("Error al actualizar rol con id " + id, ex);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            rolRepo.deleteById(id);
        } catch (DataAccessException ex) {
            throw new ServiceException("Error al eliminar rol con id " + id, ex);
        }
    }

    @Override
    public Optional<Rol> findByTipo(Rol.Tipo tipo) throws ServiceException {
        try {
            return rolRepo.findByTipo(tipo);
        } catch (DataAccessException ex) {
            throw new ServiceException("Error al buscar rol por tipo " + tipo, ex);
        }
    }
}
