package com.sofka.lab.customers.app.models.entity;



import com.sofka.lab.customers.app.models.dao.CustomerDao;
import com.sofka.lab.customers.app.models.entity.dtos.CustomerDtoImpl;
import com.sofka.lab.customers.app.models.services.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerTest {

    @Mock
    private CustomerDao customerDao;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void testFindByIdentificacion() {
        

        CustomerEntity customer = new CustomerEntity();
        customer.setName("Juan");
        customer.setAge(25);
        customer.setIdentification("123456789");
        customer.setAddress("Calle 123");
        customer.setId(1L);
        customer.setPassword("123234");
        customer.setGenre("Masculino");
        customer.setPhone("1234567890");
        customer.setStatus(true);

        CustomerDto clienteDto = CustomerDtoImpl.builder()
                .name("Juan")
                .age(25)
                .identification("123456789")
                .address("Calle 123")
                .id(1L)
                .genre("Masculino")
                .phone("1234567890")
                .status(true).build();

        when(customerDao.findDtoByIdentification("123456789")).thenReturn(clienteDto);
        when(customerService.findByIdentification("123456789")).thenReturn(clienteDto);

        CustomerDto customerRecovered = customerService.findByIdentification("123456789");

        assert customerRecovered.getName().equals(customer.getName());
        assert customerRecovered.getAge().equals(customer.getAge());
        assert customerRecovered.getIdentification().equals(customer.getIdentification());
        assert customerRecovered.getAddress().equals(customer.getAddress());
        assert customerRecovered.getId().equals(customer.getId());
        assert customerRecovered.getGenre().equals(customer.getGenre());
        assert customerRecovered.getPhone().equals(customer.getPhone());
        assert customerRecovered.getStatus().equals(customer.getStatus());

    }







}
