package com.celiahelp.service;

import com.celiahelp.model.Log;

import java.util.List;
import java.util.Optional;

public interface LogService {
    List<Log> obtenerTodos();
    Optional<Log> obtenerPorId(Long id);
    List<Log> obtenerPorIncidencia(Long incidenciaId);
    Log crear(Log log);
}
