package com.sofka.lab.customers.app.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sofka.lab.common.dtos.CustomerDto;
import com.sofka.lab.customers.app.models.dao.CustomerDao;
import com.sofka.lab.customers.app.models.entity.Customer;
import com.sofka.lab.customers.app.models.services.CustomerServiceImpl;
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

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerServiceImpl customerService;

    private ObjectMapper objectMapper;

    @Mock
    private CustomerDao customerDao;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testFindAll() throws Exception {
        Customer customer1 = new Customer();
        customer1.setName("Juan");
        customer1.setAge(25);
        customer1.setIdentification("123456789");
        customer1.setAddress("Calle 123");
        customer1.setId(1L);
        customer1.setPassword("123234");
        customer1.setGenre("Masculino");
        customer1.setPhone("1234567890");
        customer1.setStatus(true);
        
        Customer customer2 = new Customer();
        customer2.setName("Pedro");
        customer2.setAge(25);
        customer2.setIdentification("123456789");
        customer2.setAddress("Calle 123");
        customer2.setId(2L);
        customer2.setPassword("123234");
        customer2.setGenre("Masculino");
        customer2.setPhone("123456789x");
        customer2.setStatus(true);
        

        when(customerService.findAll()).thenReturn(List.of(customer1.toDto(), customer2.toDto()));

        mockMvc.perform(MockMvcRequestBuilders.get("/customers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Juan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Pedro"));

    }

    @Test
    public void testFindById() throws Exception {
        Customer customer = new Customer();
        customer.setName("Juan");
        customer.setAge(25);
        customer.setIdentification("123456789");
        customer.setAddress("Calle 123");
        customer.setId(1L);
        customer.setPassword("123234");
        customer.setGenre("Masculino");
        customer.setPhone("1234567890");
        customer.setStatus(true);

        when(customerService.findById(1L)).thenReturn(customer.toDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/customers/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Juan"));

    }


    @Test
    public void testSaveCliente() throws Exception {
        // Configurar el mock
        Customer customer = new Customer();
        customer.setName("Juan");
        customer.setAge(25);
        customer.setIdentification("123456789");
        customer.setAddress("Calle 123");
        customer.setId(1L);
        customer.setPassword("123234");
        customer.setGenre("Masculino");
        customer.setPhone("1234567890");
        customer.setStatus(true);


        CustomerDto clienteOutputDto = new CustomerDto();
        clienteOutputDto.setId(1L);
        clienteOutputDto.setName("Juan");

        when(customerService.save(any(Customer.class))).thenReturn(clienteOutputDto);


        // Realizar la solicitud POST y verificar el resultado
        mockMvc.perform(MockMvcRequestBuilders.post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(clienteOutputDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(clienteOutputDto.getName()))
        // Añadir más expectativas según la estructura de ClienteDto
        ;
    }

}
