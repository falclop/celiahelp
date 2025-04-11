package com.celiahelp.controller;

import com.celiahelp.model.Log;
import com.celiahelp.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public ResponseEntity<List<Log>> obtenerTodos() {
        return ResponseEntity.ok(logService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Log> obtenerPorId(@PathVariable Long id) {
        return logService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/incidencia/{incidenciaId}")
    public ResponseEntity<List<Log>> obtenerPorIncidencia(@PathVariable Long incidenciaId) {
        return ResponseEntity.ok(logService.obtenerPorIncidencia(incidenciaId));
    }

    @PostMapping
    public ResponseEntity<Log> crear(@RequestBody Log log) {
        return ResponseEntity.ok(logService.crear(log));
    }
}
