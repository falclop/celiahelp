// LogController.java
// LogController.java
package com.celiahelp.controller;

import com.celiahelp.dto.LogDTO;
import com.celiahelp.exception.NotFoundException;
import com.celiahelp.exception.ServiceException;
import com.celiahelp.mapper.LogMapper;
import com.celiahelp.model.Incidencia;
import com.celiahelp.model.Log;
import com.celiahelp.model.Usuario;
import com.celiahelp.repository.IncidenciaRepository;
import com.celiahelp.repository.UsuarioRepository;
import com.celiahelp.service.LogService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private final LogService logService;
    private final IncidenciaRepository incidenciaRepository;
    private final UsuarioRepository usuarioRepository;

    public LogController(LogService logService, IncidenciaRepository incidenciaRepository, UsuarioRepository usuarioRepository) {
        this.logService = logService;
        this.incidenciaRepository = incidenciaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public ResponseEntity<List<LogDTO>> getAll() throws ServiceException {
        List<Log> logs = logService.getAll();
        List<LogDTO> dtos = logs.stream()
                .map(LogMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogDTO> getById(@PathVariable Long id) throws ServiceException, NotFoundException {
        Log log = logService.getById(id);
        return ResponseEntity.ok(LogMapper.toDTO(log));
    }

    @PostMapping
    public ResponseEntity<LogDTO> create(@Valid @RequestBody LogDTO logDTO) throws ServiceException {
        Incidencia incidencia = incidenciaRepository.findById(logDTO.getIncidenciaId())
                .orElseThrow(() -> new NotFoundException("Incidencia no encontrada"));
        Usuario usuario = usuarioRepository.findById(logDTO.getUsuarioId())
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        Log log = LogMapper.toEntity(logDTO, incidencia, usuario);
        Log created = logService.create(log);
        return ResponseEntity.status(201).body(LogMapper.toDTO(created));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        logService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
