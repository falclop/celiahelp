package com.celiahelp.controller;

import com.celiahelp.model.Incidencia;
import com.celiahelp.service.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidencias")
public class IncidenciaController {

    private final IncidenciaService incidenciaService;

    @Autowired
    public IncidenciaController(IncidenciaService incidenciaService) {
        this.incidenciaService = incidenciaService;
    }

    // GET /api/incidencias
    @GetMapping
    public ResponseEntity<List<Incidencia>> obtenerTodas() {
        return ResponseEntity.ok(incidenciaService.obtenerTodas());
    }

    // GET /api/incidencias/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Incidencia> obtenerPorId(@PathVariable Long id) {
        return incidenciaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/incidencias
    @PostMapping
    public ResponseEntity<Incidencia> crear(@RequestBody Incidencia incidencia) {
        return ResponseEntity.ok(incidenciaService.crearIncidencia(incidencia));
    }

    // PUT /api/incidencias/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Incidencia> actualizar(@PathVariable Long id, @RequestBody Incidencia nuevaIncidencia) {
        try {
            return ResponseEntity.ok(incidenciaService.actualizarIncidencia(id, nuevaIncidencia));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/incidencias/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        incidenciaService.eliminarIncidencia(id);
        return ResponseEntity.noContent().build();
    }
}
