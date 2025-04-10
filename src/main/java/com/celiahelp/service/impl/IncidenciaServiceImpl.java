package com.celiahelp.service.impl;

import com.celiahelp.model.Incidencia;
import com.celiahelp.repository.IncidenciaRepository;
import com.celiahelp.service.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidenciaServiceImpl implements IncidenciaService {

    private final IncidenciaRepository incidenciaRepository;

    @Autowired
    public IncidenciaServiceImpl(IncidenciaRepository incidenciaRepository) {
        this.incidenciaRepository = incidenciaRepository;
    }

    @Override
    public List<Incidencia> obtenerTodas() {
        return incidenciaRepository.findAll();
    }

    @Override
    public Optional<Incidencia> obtenerPorId(Long id) {
        return incidenciaRepository.findById(id);
    }

    @Override
    public Incidencia crearIncidencia(Incidencia incidencia) {
        return incidenciaRepository.save(incidencia);
    }

    @Override
    public Incidencia actualizarIncidencia(Long id, Incidencia nuevaIncidencia) {
        return incidenciaRepository.findById(id)
                .map(i -> {
                    i.setTitulo(nuevaIncidencia.getTitulo());
                    i.setDescripcion(nuevaIncidencia.getDescripcion());
                    i.setPrioridad(nuevaIncidencia.getPrioridad());
                    i.setEstado(nuevaIncidencia.getEstado());
                    i.setNombreRemitente(nuevaIncidencia.getNombreRemitente());
                    i.setEmailRemitente(nuevaIncidencia.getEmailRemitente());
                    return incidenciaRepository.save(i);
                })
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));
    }

    @Override
    public void eliminarIncidencia(Long id) {
        incidenciaRepository.deleteById(id);
    }
}
