// UsuarioController.java
package com.celiahelp.controller;

import com.celiahelp.dto.UsuarioDTO;
import com.celiahelp.exception.NotFoundException;
import com.celiahelp.exception.ServiceException;
import com.celiahelp.model.Usuario;
import com.celiahelp.repository.RolRepository;
import com.celiahelp.service.UsuarioService;
import com.celiahelp.mapper.UsuarioMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        List<UsuarioDTO> dtoList = usuarios.stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getById(@PathVariable Long id)
            throws ServiceException, NotFoundException {
        Usuario usuario = usuarioService.getById(id);
        return ResponseEntity.ok(UsuarioMapper.toDTO(usuario));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO usuarioDTO) throws ServiceException {
        Usuario usuario = UsuarioMapper.toEntity(usuarioDTO, rolRepository);
        Usuario created = usuarioService.create(usuario);
        return ResponseEntity.status(201).body(UsuarioMapper.toDTO(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO)
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
