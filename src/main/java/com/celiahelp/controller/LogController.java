// LogController.java
package com.celiahelp.controller;

import com.celiahelp.exception.ServiceException;
import com.celiahelp.model.Log;
import com.celiahelp.model.Incidencia;
import com.celiahelp.model.Usuario;
import com.celiahelp.service.LogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {
    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public ResponseEntity<List<Log>> getAll() throws ServiceException {
        return ResponseEntity.ok(logService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Log> getById(@PathVariable Long id) throws ServiceException {
        return logService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Log no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<Log> create(@RequestBody Log log) throws ServiceException {
        Log created = logService.create(log);
        return ResponseEntity.status(201).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        logService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/incidencia/{incidenciaId}")
    public ResponseEntity<List<Log>> getByIncidencia(@PathVariable Long incidenciaId)
            throws ServiceException {
        Incidencia inc = new Incidencia();
        inc.setId(incidenciaId);
        return ResponseEntity.ok(logService.findByIncidencia(inc));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Log>> getByUsuario(@PathVariable Long usuarioId)
            throws ServiceException {
        Usuario usr = new Usuario();
        usr.setId(usuarioId);
        return ResponseEntity.ok(logService.findByUsuario(usr));
    }
}
