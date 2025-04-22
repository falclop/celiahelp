// LogServiceImpl.java
package com.celiahelp.service.impl;

import com.celiahelp.exception.NotFoundException;
import com.celiahelp.exception.ServiceException;
import com.celiahelp.model.Log;
import com.celiahelp.repository.LogRepository;
import com.celiahelp.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    @Override
    public List<Log> getAll() {
        return logRepository.findAll();
    }

    @Override
    public Log getById(Long id) throws NotFoundException {
        return logRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Log no encontrado con id: " + id));
    }

    @Override
    public Log create(Log log) throws ServiceException {
        try {
            return logRepository.save(log);
        } catch (Exception e) {
            throw new ServiceException("Error al crear el log", e);
        }
    }

    @Override
    public Log update(Long id, Log log) throws ServiceException, NotFoundException {
        Log existing = getById(id);
        existing.setAccion(log.getAccion());
        existing.setUsuario(log.getUsuario());
        existing.setIncidencia(log.getIncidencia());
        existing.setFecha(log.getFecha());
        return logRepository.save(existing);
    }

    @Override
    public void delete(Long id) throws ServiceException, NotFoundException {
        Log log = getById(id);
        try {
            logRepository.delete(log);
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar el log", e);
        }
    }
}
