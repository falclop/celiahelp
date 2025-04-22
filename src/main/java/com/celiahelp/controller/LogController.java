// LogController.java
package com.celiahelp.controller;

import com.celiahelp.dto.LogDTO;
import com.celiahelp.mapper.LogMapper;
import com.celiahelp.model.Log;
import com.celiahelp.model.Incidencia;
import com.celiahelp.model.Usuario;
import com.celiahelp.service.IncidenciaService;
import com.celiahelp.service.LogService;
import com.celiahelp.service.UsuarioService;
import com.celiahelp.exception.NotFoundException;
import com.celiahelp.exception.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    @Autowired
    private LogService logService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private IncidenciaService incidenciaService;

    @GetMapping
    public ResponseEntity<List<LogDTO>> getAll() {
        List<Log> logs = logService.getAll();
        List<LogDTO> logDTOs = logs.stream()
                .map(LogMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(logDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogDTO> getById(@PathVariable Long id) throws NotFoundException {
        Log log = logService.getById(id);
        return ResponseEntity.ok(LogMapper.toDTO(log));
    }

    @PostMapping
    public ResponseEntity<LogDTO> create(@RequestBody LogDTO logDTO) throws ServiceException, NotFoundException {
        Incidencia incidencia = incidenciaService.getById(logDTO.getIncidenciaId());
        Usuario usuario = usuarioService.getById(logDTO.getUsuarioId());
        Log log = LogMapper.toEntity(logDTO, incidencia, usuario);
        Log created = logService.create(log);
        return ResponseEntity.status(201).body(LogMapper.toDTO(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LogDTO> update(@PathVariable Long id, @RequestBody LogDTO logDTO)
            throws ServiceException, NotFoundException {
        Incidencia incidencia = incidenciaService.getById(logDTO.getIncidenciaId());
        Usuario usuario = usuarioService.getById(logDTO.getUsuarioId());
        Log log = LogMapper.toEntity(logDTO, incidencia, usuario);
        Log updated = logService.update(id, log);
        return ResponseEntity.ok(LogMapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws NotFoundException {
        try {
            logService.delete(id);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.noContent().build();
    }
}
