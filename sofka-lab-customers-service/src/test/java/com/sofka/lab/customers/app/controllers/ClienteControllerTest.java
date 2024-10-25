package com.sofka.lab.customers.app.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sofka.lab.common.dtos.ClienteDto;
import com.sofka.lab.customers.app.models.dao.ClienteDao;
import com.sofka.lab.customers.app.models.entity.Cliente;
import com.sofka.lab.customers.app.models.services.ClienteServiceImpl;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteServiceImpl clienteService;

    private ObjectMapper objectMapper;

    @Mock
    private ClienteDao clienteDao;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testFindAll() throws Exception {
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Juan");
        cliente1.setEdad(25);
        cliente1.setIdentificacion("123456789");
        cliente1.setDireccion("Calle 123");
        cliente1.setId(1L);
        cliente1.setContrasena("123234");
        cliente1.setGenero("Masculino");
        cliente1.setTelefono("1234567890");
        cliente1.setEstado(true);

        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Pedro");
        cliente2.setEdad(30);
        cliente2.setIdentificacion("123456789");
        cliente2.setDireccion("Calle 123");
        cliente2.setId(2L);
        cliente2.setContrasena("123234");
        cliente2.setGenero("Masculino");
        cliente2.setTelefono("1234567890");
        cliente2.setEstado(true);

        when(clienteService.findAll()).thenReturn(List.of(cliente1.toDto(), cliente2.toDto()));

        mockMvc.perform(MockMvcRequestBuilders.get("/clientes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre").value("Juan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nombre").value("Pedro"));

    }

    @Test
    public void testFindById() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setEdad(25);
        cliente.setIdentificacion("123456789");
        cliente.setDireccion("Calle 123");
        cliente.setId(1L);
        cliente.setContrasena("123234");
        cliente.setGenero("Masculino");
        cliente.setTelefono("1234567890");
        cliente.setEstado(true);

        when(clienteService.findById(1L)).thenReturn(cliente.toDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Juan"));

    }


    @Test
    public void testSaveCliente() throws Exception {
        // Configurar el mock
        Cliente clienteInput = new Cliente();
        clienteInput.setNombre("Juan");
        clienteInput.setEdad(25);
        clienteInput.setIdentificacion("123456789");
        clienteInput.setDireccion("Calle 123");
        clienteInput.setId(1L);
        clienteInput.setContrasena("123234");
        clienteInput.setGenero("Masculino");
        clienteInput.setTelefono("1234567890");
        clienteInput.setEstado(true);
        ClienteDto clienteOutputDto = new ClienteDto();
        clienteOutputDto.setId(1L);
        clienteOutputDto.setNombre("Juan");

        when(clienteService.save(any(Cliente.class))).thenReturn(clienteOutputDto);


        // Realizar la solicitud POST y verificar el resultado
        mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteInput)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(clienteOutputDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value(clienteOutputDto.getNombre()))
        // Añadir más expectativas según la estructura de ClienteDto
        ;
    }

}
