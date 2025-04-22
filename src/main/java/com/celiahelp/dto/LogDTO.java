package com.celiahelp.dto;

import com.celiahelp.model.Log;

import java.time.LocalDateTime;

public class LogDTO {
    private Long id;
    private String accion;
    private LocalDateTime fecha;
    private UsuarioDTO usuario;

    public LogDTO(Long id, Log.Accion accion, LocalDateTime fecha, UsuarioDTO dto) {}

    public LogDTO(Long id, String accion, LocalDateTime fecha, UsuarioDTO usuario) {
        this.id = id;
        this.accion = accion;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Log.Accion getAccion() { return accion; }
    public void setAccion(String accion) { this.accion = accion; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public UsuarioDTO getUsuario() { return usuario; }
    public void setUsuario(UsuarioDTO usuario) { this.usuario = usuario; }
}
