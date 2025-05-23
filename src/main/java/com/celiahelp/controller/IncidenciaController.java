package com.celiahelp.controller;

import com.celiahelp.dto.IncidenciaDTO;
import com.celiahelp.exception.NotFoundException;
import com.celiahelp.exception.ServiceException;
import com.celiahelp.mapper.IncidenciaMapper;
import com.celiahelp.model.Incidencia;
import com.celiahelp.repository.UsuarioRepository;
import com.celiahelp.service.IncidenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidencias")
@Validated
@Tag(name = "Incidencias", description = "API para gestionar incidencias")
public class IncidenciaController {

    private final IncidenciaService incidenciaService;
    private final UsuarioRepository usuarioRepository;

    public IncidenciaController(IncidenciaService incidenciaService,
                                UsuarioRepository usuarioRepository) {
        this.incidenciaService = incidenciaService;
        this.usuarioRepository = usuarioRepository;
    }

    @Operation(summary = "Listar todas las incidencias",
            description = "Obtiene la lista completa de incidencias registradas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de incidencias devuelta correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IncidenciaDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<IncidenciaDTO>> getAll() throws ServiceException {
        List<Incidencia> incidencias = incidenciaService.getAll();
        List<IncidenciaDTO> dtos = incidencias.stream()
                .map(IncidenciaMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Obtener incidencia por ID",
            description = "Busca una incidencia según su identificador único")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Incidencia encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IncidenciaDTO.class))),
            @ApiResponse(responseCode = "404", description = "No se encontró la incidencia",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<IncidenciaDTO> getById(
            @Parameter(in = ParameterIn.PATH,
                    description = "ID de la incidencia",
                    example = "42", required = true)
            @PathVariable Long id) throws ServiceException, NotFoundException {
        Incidencia incidencia = incidenciaService.getById(id);
        return ResponseEntity.ok(IncidenciaMapper.toDTO(incidencia));
    }

    @Operation(summary = "Crear una nueva incidencia",
            description = "Cualquier usuario puede crear una incidencia sin autenticarse")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Incidencia creada correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IncidenciaDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<IncidenciaDTO> create(
            @Parameter(description = "Datos de la incidencia a crear", required = true)
            @RequestBody @Valid IncidenciaDTO incidenciaDTO) throws ServiceException {
        Incidencia incidencia = IncidenciaMapper.toEntity(incidenciaDTO, usuarioRepository);
        Incidencia created = incidenciaService.create(incidencia);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(IncidenciaMapper.toDTO(created));
    }

    @Operation(summary = "Actualizar una incidencia existente",
            description = "Solo gestores y administradores autenticados pueden actualizar")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Incidencia actualizada correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IncidenciaDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontró la incidencia",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<IncidenciaDTO> update(
            @Parameter(in = ParameterIn.PATH,
                    description = "ID de la incidencia a actualizar",
                    example = "42", required = true)
            @PathVariable Long id,
            @Parameter(description = "Nuevos datos de la incidencia", required = true)
            @RequestBody @Valid IncidenciaDTO incidenciaDTO)
            throws ServiceException, NotFoundException {
        Incidencia incidencia = IncidenciaMapper.toEntity(incidenciaDTO, usuarioRepository);
        Incidencia updated = incidenciaService.update(id, incidencia);
        return ResponseEntity.ok(IncidenciaMapper.toDTO(updated));
    }

    @Operation(summary = "Eliminar una incidencia",
            description = "Solo gestores y administradores autenticados pueden eliminar")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Incidencia eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontró la incidencia",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(in = ParameterIn.PATH,
                    description = "ID de la incidencia a eliminar",
                    example = "42", required = true)
            @PathVariable Long id) throws ServiceException {
        incidenciaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
