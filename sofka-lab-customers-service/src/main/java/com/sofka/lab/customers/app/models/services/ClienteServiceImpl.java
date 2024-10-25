package com.sofka.lab.customers.app.models.services;

import com.sofka.lab.common.dtos.ClienteDto;
import com.sofka.lab.customers.app.models.dao.ClienteDao;
import com.sofka.lab.customers.app.models.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

  @Autowired
  private ClienteDao clienteDao;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public List<ClienteDto> findAll() {
    return clienteDao.findAllDto();
  }

  @Override
  public ClienteDto findById(Long id) {
    if (clienteDao.findById(id).isPresent()) {
      return clienteDao.findByIdDto(id);
    }
    return null;
  }

  @Override
  public ClienteDto save(Cliente cliente) {
    cliente.setContrasena(passwordEncoder.encode(cliente.getContrasena()));
    clienteDao.save(cliente);
    return cliente.toDto();
  }

  @Override
  public ClienteDto update(ClienteDto cliente) {
    this.clienteDao.findById(cliente.getId()).ifPresent(cliente1 -> {
      cliente1.setNombre(cliente.getNombre());
      cliente1.setGenero(cliente.getGenero());
      cliente1.setEdad(cliente.getEdad());
      cliente1.setDireccion(cliente.getDireccion());
      cliente1.setTelefono(cliente.getTelefono());
      cliente1.setEstado(cliente.getEstado());
      clienteDao.save(cliente1);
    });
    return cliente;
  }

  @Override
  public void delete(Long id) {
    clienteDao.deleteById(id);
  }

  @Override
  public ClienteDto findByIdentificacion(String identificacion) {
    return clienteDao.findByIdentificacionDto(identificacion);
  }
}
