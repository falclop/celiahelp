// IncidenciaServiceImpl.java
package com.celiahelp.service.impl;

import com.celiahelp.exception.NotFoundException;
import com.celiahelp.exception.ServiceException;
import com.celiahelp.model.Incidencia;
import com.celiahelp.repository.IncidenciaRepository;
import com.celiahelp.service.IncidenciaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidenciaServiceImpl implements IncidenciaService {

    private final IncidenciaRepository repo;

    public IncidenciaServiceImpl(IncidenciaRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Incidencia> findAll() throws ServiceException {
        try {
            return repo.findAll();
        } catch (Exception e) {
            throw new ServiceException("Error al listar incidencias", e);
        }
    }

    @Override
    public Optional<Incidencia> findById(Long id) throws ServiceException {
        try {
            return repo.findById(id);
        } catch (Exception e) {
            throw new ServiceException("Error al buscar incidencia con id " + id, e);
        }
    }

    @Override
    public Incidencia create(Incidencia incidencia) throws ServiceException {
        try {
            return repo.save(incidencia);
        } catch (Exception e) {
            throw new ServiceException("Error al crear incidencia", e);
        }
    }

    @Override
    public Incidencia update(Long id, Incidencia incidencia) throws ServiceException, NotFoundException {
        try {
            return repo.findById(id)
                    .map(existing -> {
                        // actualizar campos
                        existing.setTitulo(incidencia.getTitulo());
                        existing.setDescripcion(incidencia.getDescripcion());
                        existing.setEstado(incidencia.getEstado());
                        existing.setPrioridad(incidencia.getPrioridad());
                        existing.setGestionadaPor(incidencia.getGestionadaPor());
                        return repo.save(existing);
                    })
                    .orElseThrow(() -> new NotFoundException("Incidencia no encontrada con id " + id));
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar incidencia con id " + id, e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            if (!repo.existsById(id)) {
                throw new NotFoundException("Incidencia no encontrada con id " + id);
            }
            repo.deleteById(id);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar incidencia con id " + id, e);
        }
    }
}
