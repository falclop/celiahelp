// LogServiceImpl.java
package com.celiahelp.service.impl;

import com.celiahelp.exception.NotFoundException;
import com.celiahelp.exception.ServiceException;
import com.celiahelp.model.Log;
import com.celiahelp.model.Incidencia;
import com.celiahelp.model.Usuario;
import com.celiahelp.repository.LogRepository;
import com.celiahelp.service.LogService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public List<Log> findAll() throws ServiceException {
        try {
            return logRepository.findAll();
        } catch (Exception e) {
            throw new ServiceException("Error al listar logs", e);
        }
    }

    @Override
    public Optional<Log> findById(Long id) throws ServiceException {
        try {
            return logRepository.findById(id);
        } catch (Exception e) {
            throw new ServiceException("Error al buscar log con id " + id, e);
        }
    }

    @Override
    public Log create(Log log) throws ServiceException {
        try {
            return logRepository.save(log);
        } catch (Exception e) {
            throw new ServiceException("Error al crear log", e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            if (!logRepository.existsById(id)) {
                throw new NotFoundException("Log no encontrado con id " + id);
            }
            logRepository.deleteById(id);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar log con id " + id, e);
        }
    }

    @Override
    public List<Log> findByIncidencia(Incidencia incidencia) throws ServiceException {
        try {
            return logRepository.findByIncidencia(incidencia);
        } catch (Exception e) {
            throw new ServiceException("Error al listar logs de la incidencia " + incidencia.getId(), e);
        }
    }

    @Override
    public List<Log> findByUsuario(Usuario usuario) throws ServiceException {
        try {
            return logRepository.findByUsuario(usuario);
        } catch (Exception e) {
            throw new ServiceException("Error al listar logs del usuario " + usuario.getId(), e);
        }
    }
}
