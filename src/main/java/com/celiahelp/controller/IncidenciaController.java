// IncidenciaController.java
package com.celiahelp.controller;

import com.celiahelp.dto.IncidenciaDTO;
import com.celiahelp.exception.NotFoundException;
import com.celiahelp.exception.ServiceException;
import com.celiahelp.mapper.IncidenciaMapper;
import com.celiahelp.model.Incidencia;
import com.celiahelp.repository.UsuarioRepository;
import com.celiahelp.service.IncidenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidencias")
public class IncidenciaController {

    private final IncidenciaService incidenciaService;
    private final UsuarioRepository usuarioRepository;

    public IncidenciaController(IncidenciaService incidenciaService, UsuarioRepository usuarioRepository) {
        this.incidenciaService = incidenciaService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public ResponseEntity<List<IncidenciaDTO>> getAll() throws ServiceException {
        List<Incidencia> incidencias = incidenciaService.getAll();
        List<IncidenciaDTO> dtos = incidencias.stream()
                .map(IncidenciaMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidenciaDTO> getById(@PathVariable Long id) throws ServiceException, NotFoundException {
        Incidencia incidencia = incidenciaService.getById(id);
        return ResponseEntity.ok(IncidenciaMapper.toDTO(incidencia));
    }

    @PostMapping
    public ResponseEntity<IncidenciaDTO> create(@RequestBody IncidenciaDTO incidenciaDTO) throws ServiceException {
        Incidencia incidencia = IncidenciaMapper.toEntity(incidenciaDTO, usuarioRepository);
        Incidencia created = incidenciaService.create(incidencia);
        return ResponseEntity.status(201).body(IncidenciaMapper.toDTO(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncidenciaDTO> update(@PathVariable Long id, @RequestBody IncidenciaDTO incidenciaDTO)
            throws ServiceException, NotFoundException {
        Incidencia incidencia = IncidenciaMapper.toEntity(incidenciaDTO, usuarioRepository);
        Incidencia updated = incidenciaService.update(id, incidencia);
        return ResponseEntity.ok(IncidenciaMapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        incidenciaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
