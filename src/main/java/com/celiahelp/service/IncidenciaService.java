package com.celiahelp.service;

import com.celiahelp.model.Incidencia;

import java.util.List;
import java.util.Optional;

public interface IncidenciaService {

    List<Incidencia> obtenerTodas();

    Optional<Incidencia> obtenerPorId(Long id);

    Incidencia crearIncidencia(Incidencia incidencia);

    Incidencia actualizarIncidencia(Long id, Incidencia incidencia);

    void eliminarIncidencia(Long id);
}
