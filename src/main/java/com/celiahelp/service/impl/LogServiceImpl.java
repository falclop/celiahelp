package com.celiahelp.service.impl;

import com.celiahelp.model.Log;
import com.celiahelp.repository.LogRepository;
import com.celiahelp.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public List<Log> obtenerTodos() {
        return logRepository.findAll();
    }

    @Override
    public Optional<Log> obtenerPorId(Long id) {
        return logRepository.findById(id);
    }

    @Override
    public List<Log> obtenerPorIncidencia(Long incidenciaId) {
        return logRepository.findByIncidenciaId(incidenciaId);
    }

    @Override
    public Log crear(Log log) {
        if (log.getFecha() == null) {
            log.setFecha(LocalDateTime.now());
        }
        return logRepository.save(log);
    }
}
