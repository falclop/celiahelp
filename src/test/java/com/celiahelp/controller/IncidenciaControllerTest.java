package com.celiahelp.controller;

import com.celiahelp.dto.IncidenciaDTO;
import com.celiahelp.model.Incidencia;
import com.celiahelp.repository.UsuarioRepository;
import com.celiahelp.service.IncidenciaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(IncidenciaController.class)
class IncidenciaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private IncidenciaService incidenciaService;
    @MockitoBean
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAll_deberiaRetornarOKyJSON() throws Exception {
        Incidencia sample = new Incidencia();
        sample.setId(1L);
        sample.setTitulo("T1");
        sample.setDescripcion("D1");
        when(incidenciaService.getAll()).thenReturn(List.of(sample));

        mockMvc.perform(get("/api/incidencias"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1));
    }


    @Test
    void getById_existente_devuelveOK() throws Exception {
        Incidencia sample = new Incidencia();
        sample.setId(2L);
        sample.setTitulo("T2");
        when(incidenciaService.getById(2L)).thenReturn(sample);

        mockMvc.perform(get("/api/incidencias/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2));
    }

    @Test
    void create_valido_devuelve201() throws Exception {
        IncidenciaDTO dto = new IncidenciaDTO(null,"T","D","BAJA","PENDIENTE",
                LocalDateTime.now(),"N","e@mail",null);
        Incidencia saved = new Incidencia();
        saved.setId(5L);
        when(incidenciaService.create(any())).thenReturn(saved);

        mockMvc.perform(post("/api/incidencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(5));
        verify(incidenciaService).create(any());
    }

    @Test
    void delete_invocaServicio_devuelve204() throws Exception {
        doNothing().when(incidenciaService).delete(3L);
        mockMvc.perform(delete("/api/incidencias/3"))
                .andExpect(status().isNoContent());
        verify(incidenciaService).delete(3L);
    }
}
