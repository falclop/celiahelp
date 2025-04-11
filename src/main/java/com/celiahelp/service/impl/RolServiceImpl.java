package com.celiahelp.service.impl;

import com.celiahelp.model.Rol;
import com.celiahelp.repository.RolRepository;
import com.celiahelp.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    @Autowired
    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public List<Rol> obtenerTodos() {
        return rolRepository.findAll();
    }

    @Override
    public Optional<Rol> obtenerPorId(Long id) {
        return rolRepository.findById(id);
    }

    @Override
    public Rol crear(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public Rol actualizar(Long id, Rol nuevo) {
        return rolRepository.findById(id)
                .map(r -> {
                    r.setTipo(nuevo.getTipo());
                    return rolRepository.save(r);
                })
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        rolRepository.deleteById(id);
    }
}
