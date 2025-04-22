package com.celiahelp.service;

import com.celiahelp.exception.NotFoundException;
import com.celiahelp.exception.ServiceException;
import com.celiahelp.model.Incidencia;
import com.celiahelp.repository.IncidenciaRepository;
import com.celiahelp.service.impl.IncidenciaServiceImpl;
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
class IncidenciaServiceTest {

    @Mock
    private IncidenciaRepository incidenciaRepository;

    @InjectMocks
    private IncidenciaServiceImpl incidenciaService;

    private Incidencia sample;

    @BeforeEach
    void setUp() {
        sample = new Incidencia();
        sample.setId(1L);
        sample.setTitulo("T1");
        sample.setDescripcion("D1");
        // resto de campos si los necesitas...
    }

    @Test
    void getAll_deberiaDelegarARepository() throws ServiceException {
        when(incidenciaRepository.findAll()).thenReturn(List.of(sample));
        List<Incidencia> todas = incidenciaService.getAll();
        assertEquals(1, todas.size());
        verify(incidenciaRepository).findAll();
    }

    @Test
    void getById_existente_devuelveEntidad() throws Exception {
        when(incidenciaRepository.findById(1L)).thenReturn(Optional.of(sample));
        Incidencia found = incidenciaService.getById(1L);
        assertSame(sample, found);
        verify(incidenciaRepository).findById(1L);
    }

    @Test
    void getById_noExiste_lanzaNotFound() {
        when(incidenciaRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> incidenciaService.getById(2L));
    }

    @Test
    void create_validaYGuarda() throws ServiceException {
        when(incidenciaRepository.save(sample)).thenReturn(sample);
        Incidencia created = incidenciaService.create(sample);
        assertSame(sample, created);
        verify(incidenciaRepository).save(sample);
    }

    @Test
    void update_existente_actualizaCampos() throws Exception {
        Incidencia updatedData = new Incidencia();
        updatedData.setTitulo("Nuevo");
        when(incidenciaRepository.findById(1L)).thenReturn(Optional.of(sample));
        when(incidenciaRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Incidencia result = incidenciaService.update(1L, updatedData);
        assertEquals("Nuevo", result.getTitulo());
        verify(incidenciaRepository).findById(1L);
        verify(incidenciaRepository).save(sample);
    }

    @Test
    void update_noExiste_lanzaNotFound() {
        when(incidenciaRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> incidenciaService.update(99L, sample));
    }

    @Test
    void delete_existente_invocaBorrado() throws ServiceException {
        when(incidenciaRepository.existsById(1L)).thenReturn(true);
        incidenciaService.delete(1L);
        verify(incidenciaRepository).deleteById(1L);
    }

    @Test
    void delete_noExiste_lanzaServiceException() {
        when(incidenciaRepository.existsById(2L)).thenReturn(false);
        assertThrows(ServiceException.class, () -> incidenciaService.delete(2L));
    }
}
