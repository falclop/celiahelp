// IncidenciaServiceImpl.java
package com.celiahelp.service.impl;

import com.celiahelp.exception.NotFoundException;
import com.celiahelp.exception.ServiceException;
import com.celiahelp.model.Incidencia;
import com.celiahelp.repository.IncidenciaRepository;
import com.celiahelp.service.IncidenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncidenciaServiceImpl implements IncidenciaService {

    private final IncidenciaRepository incidenciaRepository;

    @Override
    public List<Incidencia> getAll() {
        return incidenciaRepository.findAll();
    }

    @Override
    public Incidencia getById(Long id) throws NotFoundException {
        return incidenciaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Incidencia no encontrada con id: " + id));
    }

    @Override
    public Incidencia create(Incidencia incidencia) throws ServiceException {
        try {
            return incidenciaRepository.save(incidencia);
        } catch (Exception e) {
            throw new ServiceException("Error al crear la incidencia", e);
        }
    }

    @Override
    public Incidencia update(Long id, Incidencia incidencia) throws ServiceException, NotFoundException {
        Incidencia existente = getById(id);
        incidencia.setId(existente.getId());
        return incidenciaRepository.save(incidencia);
    }

    @Override
    public void delete(Long id) throws ServiceException, NotFoundException {
        Incidencia incidencia = getById(id);
        try {
            incidenciaRepository.delete(incidencia);
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar la incidencia", e);
        }
    }
}
