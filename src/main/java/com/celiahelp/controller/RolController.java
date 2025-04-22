// RolController.java
package com.celiahelp.controller;

import com.celiahelp.exception.NotFoundException;
import com.celiahelp.exception.ServiceException;
import com.celiahelp.model.Rol;
import com.celiahelp.service.RolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {
    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public ResponseEntity<List<Rol>> getAll() throws ServiceException {
        return ResponseEntity.ok(rolService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> getById(@PathVariable Long id)
            throws ServiceException, NotFoundException {
        return rolService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("Rol no encontrado con id " + id));
    }

    @PostMapping
    public ResponseEntity<Rol> create(@RequestBody Rol rol) throws ServiceException {
        Rol created = rolService.create(rol);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> update(@PathVariable Long id, @RequestBody Rol rol)
            throws ServiceException, NotFoundException {
        Rol updated = rolService.update(id, rol);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        rolService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
