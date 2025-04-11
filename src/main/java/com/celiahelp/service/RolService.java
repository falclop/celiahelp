package com.celiahelp.service;

import com.celiahelp.model.Rol;

import java.util.List;
import java.util.Optional;

public interface RolService {
    List<Rol> obtenerTodos();
    Optional<Rol> obtenerPorId(Long id);
    Rol crear(Rol rol);
    Rol actualizar(Long id, Rol nuevo);
    void eliminar(Long id);
}
