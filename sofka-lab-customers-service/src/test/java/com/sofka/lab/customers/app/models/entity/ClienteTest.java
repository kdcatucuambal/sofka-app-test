package com.sofka.lab.customers.app.models.entity;


import com.sofka.lab.common.models.dtos.ClienteDto;
import com.sofka.lab.customers.app.models.dao.ClienteDao;
import com.sofka.lab.customers.app.models.services.ClienteService;
import com.sofka.lab.customers.app.models.services.ClienteServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteTest {

    @Mock
    private ClienteDao clienteDao;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Test
    void testFindByIdentificacion() {

        Cliente cliente = new Cliente();

        cliente.setNombre("Juan");
        cliente.setEdad(25);
        cliente.setIdentificacion("123456789");
        cliente.setDireccion("Calle 123");
        cliente.setId(1L);
        cliente.setContrasena("123456");
        cliente.setGenero("Masculino");
        cliente.setTelefono("1234567890");
        cliente.setEstado(true);

        ClienteDto clienteDto = cliente.toDto();

        when(clienteDao.findByIdentificacionDto("123456789")).thenReturn(clienteDto);
        when(clienteService.findByIdentificacion("123456789")).thenReturn(clienteDto);

        ClienteDto clienteRecovered = clienteService.findByIdentificacion("123456789");

        assert clienteRecovered.getNombre().equals(cliente.getNombre());
        assert clienteRecovered.getEdad().equals(cliente.getEdad());
        assert clienteRecovered.getIdentificacion().equals(cliente.getIdentificacion());
        assert clienteRecovered.getDireccion().equals(cliente.getDireccion());
        assert clienteRecovered.getId().equals(cliente.getId());
        assert clienteRecovered.getGenero().equals(cliente.getGenero());
        assert clienteRecovered.getTelefono().equals(cliente.getTelefono());
        assert clienteRecovered.getEstado().equals(cliente.getEstado());

    }







}
