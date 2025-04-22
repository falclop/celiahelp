package com.celiahelp.dto;

import com.celiahelp.model.Rol;

public class RolDTO {
    private Long id;
    private String tipo;

    public RolDTO(Long id, Rol.Tipo tipo) {}

    public RolDTO(Long id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Rol.Tipo getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
