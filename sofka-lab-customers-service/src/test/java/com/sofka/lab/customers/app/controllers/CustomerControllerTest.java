package com.sofka.lab.customers.app.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sofka.bank.objects.Customer;
import com.sofka.bank.objects.CustomerGETAllRs;
import com.sofka.bank.objects.CustomerGETByCodeRs;
import com.sofka.lab.common.dtos.CustomerDto;
import com.sofka.lab.customers.app.handlers.CustomerHandlerServiceImpl;
import com.sofka.lab.customers.app.models.dao.CustomerDao;
import com.sofka.lab.customers.app.models.entity.CustomerEntity;
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

    @MockBean
    private CustomerHandlerServiceImpl customerHandlerService;

    private ObjectMapper objectMapper;

    @Mock
    private CustomerDao customerDao;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testFindAll() throws Exception {
        var customer1 = Customer.builder()
                .name("Juan")
                .age(25)
                .identification("123456789")
                .address("Calle 123")
                .id(1L)
                .genre("Masculino")
                .phone("1234567890")
                .status(true).build();
        var customer2 = Customer.builder()
                .id(2L)
                .name("Pedro")
                .age(25)
                .identification("123456789")
                .address("Calle 123")
                .genre("Masculino")
                .phone("123456789x")
                .status(true).build();
        CustomerGETAllRs customerGETAllRs = new CustomerGETAllRs();
        customerGETAllRs.getCustomers().add(customer1);
        customerGETAllRs.getCustomers().add(customer2);
        when(customerHandlerService.execCustomerGETAll()).thenReturn(customerGETAllRs);
        mockMvc.perform(MockMvcRequestBuilders.get("/customers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customers[0].name").value("Juan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customers[1].name").value("Pedro"));

    }

    @Test
    public void testFindById() throws Exception {
        var customerGETByCodeRs = new CustomerGETByCodeRs();
        var customer = Customer.builder()
                .name("Juan")
                .age(25)
                .identification("123456789")
                .address("Calle 123")
                .id(1L)
                .genre("Masculino")
                .phone("1234567890")
                .status(true).build();
        customerGETByCodeRs.setCustomer(customer);
        when(customerHandlerService.execCustomerGETByCode(1L)).thenReturn(customerGETByCodeRs);

        mockMvc.perform(MockMvcRequestBuilders.get("/customers/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customer.name").value("Juan"));

    }


    @Test
    public void testSaveCliente() throws Exception {
//        // Configurar el mock
//        CustomerEntity customer = new CustomerEntity();
//        customer.setName("Juan");
//        customer.setAge(25);
//        customer.setIdentification("123456789");
//        customer.setAddress("Calle 123");
//        customer.setId(1L);
//        customer.setPassword("123234");
//        customer.setGenre("Masculino");
//        customer.setPhone("1234567890");
//        customer.setStatus(true);
//
//
//        CustomerDto clienteOutputDto = new CustomerDto();
//        clienteOutputDto.setId(1L);
//        clienteOutputDto.setName("Juan");
//
//        when(customerService.save(any(CustomerEntity.class))).thenReturn(clienteOutputDto);
//
//
//        // Realizar la solicitud POST y verificar el resultado
//        mockMvc.perform(MockMvcRequestBuilders.post("/customers")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(customer)))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(clienteOutputDto.getId()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(clienteOutputDto.getName()))
//        // Añadir más expectativas según la estructura de ClienteDto
//        ;
    }

}
