package com.celiahelp.service;

import com.celiahelp.exception.NotFoundException;
import com.celiahelp.exception.ServiceException;
import com.celiahelp.model.Usuario;
import com.celiahelp.repository.UsuarioRepository;
import com.celiahelp.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private Usuario sample;

    @BeforeEach
    void setUp() {
        sample = new Usuario();
        sample.setId(1L);
        sample.setNombre("Juan");
        sample.setEmail("juan@example.com");
        sample.setPasswordHash("hashed");
        // no hace falta setRol para estos tests básicos
    }

    @Test
    void getAll_deberiaRetornarLista() throws ServiceException {
        when(usuarioRepository.findAll()).thenReturn(List.of(sample));
        List<Usuario> all = usuarioService.getAll();
        assertEquals(1, all.size());
        verify(usuarioRepository).findAll();
    }

    @Test
    void getById_existente_devuelveUsuario() throws Exception {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(sample));
        Usuario u = usuarioService.getById(1L);
        assertSame(sample, u);
        verify(usuarioRepository).findById(1L);
    }

    @Test
    void getById_noExiste_lanzaNotFound() {
        when(usuarioRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> usuarioService.getById(2L));
    }

    @Test
    void create_deberiaGuardarUsuario() throws ServiceException {
        when(usuarioRepository.save(sample)).thenReturn(sample);
        Usuario created = usuarioService.create(sample);
        assertSame(sample, created);
        verify(usuarioRepository).save(sample);
    }

    @Test
    void update_existente_actualizaYGuarda() throws Exception {
        Usuario cambios = new Usuario();
        cambios.setNombre("Pedro");
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(sample));
        when(usuarioRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Usuario result = usuarioService.update(1L, cambios);
        assertEquals("Pedro", result.getNombre());
        verify(usuarioRepository).findById(1L);
        verify(usuarioRepository).save(sample);
    }

    @Test
    void update_noExiste_lanzaNotFound() {
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> usuarioService.update(99L, sample));
    }

    @Test
    void delete_existente_invocaBorrado() throws ServiceException {
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        usuarioService.delete(1L);
        verify(usuarioRepository).deleteById(1L);
    }

    @Test
    void delete_noExiste_lanzaServiceException() {
        when(usuarioRepository.existsById(2L)).thenReturn(false);
        assertThrows(ServiceException.class, () -> usuarioService.delete(2L));
    }
}
