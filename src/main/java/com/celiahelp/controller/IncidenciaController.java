// IncidenciaController.java
package com.celiahelp.controller;

import com.celiahelp.exception.NotFoundException;
import com.celiahelp.exception.ServiceException;
import com.celiahelp.model.Incidencia;
import com.celiahelp.service.IncidenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidencias")
public class IncidenciaController {
    private final IncidenciaService incidenciaService;

    public IncidenciaController(IncidenciaService incidenciaService) {
        this.incidenciaService = incidenciaService;
    }

    @GetMapping
    public ResponseEntity<List<Incidencia>> getAll() throws ServiceException {
        return ResponseEntity.ok(incidenciaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incidencia> getById(@PathVariable Long id)
            throws ServiceException, NotFoundException {
        return incidenciaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("Incidencia no encontrada con id " + id));
    }

    @PostMapping
    public ResponseEntity<Incidencia> create(@RequestBody Incidencia incidencia)
            throws ServiceException {
        Incidencia created = incidenciaService.create(incidencia);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Incidencia> update(@PathVariable Long id,
                                             @RequestBody Incidencia incidencia)
            throws ServiceException, NotFoundException {
        Incidencia updated = incidenciaService.update(id, incidencia);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        incidenciaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
