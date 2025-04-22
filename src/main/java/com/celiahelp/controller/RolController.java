// RolController.java
package com.celiahelp.controller;

import com.celiahelp.dto.RolDTO;
import com.celiahelp.exception.NotFoundException;
import com.celiahelp.exception.ServiceException;
import com.celiahelp.mapper.RolMapper;
import com.celiahelp.model.Rol;
import com.celiahelp.service.RolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public ResponseEntity<List<RolDTO>> getAll() throws ServiceException {
        List<RolDTO> dtos = rolService.getAll().stream()
                .map(RolMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDTO> getById(@PathVariable Long id)
            throws ServiceException, NotFoundException {
        Rol rol = rolService.getById(id);
        return ResponseEntity.ok(RolMapper.toDTO(rol));
    }

    @PostMapping
    public ResponseEntity<RolDTO> create(@RequestBody RolDTO rolDTO)
            throws ServiceException {
        Rol created = rolService.create(RolMapper.toEntity(rolDTO));
        return ResponseEntity.status(201).body(RolMapper.toDTO(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDTO> update(@PathVariable Long id,
                                         @RequestBody RolDTO rolDTO)
            throws ServiceException, NotFoundException {
        Rol updated = rolService.update(id, RolMapper.toEntity(rolDTO));
        return ResponseEntity.ok(RolMapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        rolService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
