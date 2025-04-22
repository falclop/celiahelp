// UsuarioController.java
package com.celiahelp.controller;

import com.celiahelp.dto.UsuarioDTO;
import com.celiahelp.exception.NotFoundException;
import com.celiahelp.exception.ServiceException;
import com.celiahelp.mapper.UsuarioMapper;
import com.celiahelp.model.Usuario;
import com.celiahelp.repository.RolRepository;
import com.celiahelp.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final RolRepository rolRepository;

    public UsuarioController(UsuarioService usuarioService, RolRepository rolRepository) {
        this.usuarioService = usuarioService;
        this.rolRepository = rolRepository;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAll() throws ServiceException {
        List<Usuario> usuarios = usuarioService.getAll();
        List<UsuarioDTO> dtos = usuarios.stream()
                .map(UsuarioMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getById(@PathVariable Long id) throws ServiceException, NotFoundException {
        Usuario usuario = usuarioService.getById(id);
        return ResponseEntity.ok(UsuarioMapper.toDTO(usuario));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioDTO usuarioDTO) throws ServiceException {
        Usuario usuario = UsuarioMapper.toEntity(usuarioDTO, rolRepository);
        Usuario created = usuarioService.create(usuario);
        return ResponseEntity.status(201).body(UsuarioMapper.toDTO(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO)
            throws ServiceException, NotFoundException {
        Usuario usuario = UsuarioMapper.toEntity(usuarioDTO, rolRepository);
        Usuario updated = usuarioService.update(id, usuario);
        return ResponseEntity.ok(UsuarioMapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
