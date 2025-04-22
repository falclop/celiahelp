// RolController.java
// RolController.java
package com.celiahelp.controller;

import com.celiahelp.dto.RolDTO;
import com.celiahelp.exception.NotFoundException;
import com.celiahelp.exception.ServiceException;
import com.celiahelp.mapper.RolMapper;
import com.celiahelp.model.Rol;
import com.celiahelp.service.RolService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<RolDTO>> getAll() throws ServiceException {
        List<Rol> roles = rolService.getAll();
        List<RolDTO> dtos = roles.stream()
                .map(RolMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDTO> getById(@PathVariable Long id) throws ServiceException, NotFoundException {
        Rol rol = rolService.getById(id);
        return ResponseEntity.ok(RolMapper.toDTO(rol));
    }

    @PostMapping
    public ResponseEntity<RolDTO> create(@Valid @RequestBody RolDTO rolDTO) throws ServiceException {
        Rol rol = RolMapper.toEntity(rolDTO);
        Rol created = rolService.create(rol);
        return ResponseEntity.status(201).body(RolMapper.toDTO(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDTO> update(@PathVariable Long id, @Valid @RequestBody RolDTO rolDTO)
            throws ServiceException, NotFoundException {
        Rol rol = RolMapper.toEntity(rolDTO);
        Rol updated = rolService.update(id, rol);
        return ResponseEntity.ok(RolMapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        rolService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
