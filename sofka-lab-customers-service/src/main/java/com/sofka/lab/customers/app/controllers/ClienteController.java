package com.sofka.lab.customers.app.controllers;

import com.sofka.lab.common.dtos.ClienteDto;
import com.sofka.lab.customers.app.models.entity.Cliente;
import com.sofka.lab.customers.app.models.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {


    @Autowired
    private ClienteService clienteService;


    @GetMapping("/test")
    public String test() {
        return "Test";
    }


    @GetMapping
    public List<ClienteDto> findAll() {
        return clienteService.findAll();
    }


    @GetMapping("/{id}")
    public ClienteDto findById(@PathVariable Long id) {
        return clienteService.findById(id);
    }

    @GetMapping("/identificacion/{identificacion}")
    public ClienteDto findByIdentificacion(@PathVariable String identificacion) {
        return clienteService.findByIdentificacion(identificacion);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDto save(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        clienteService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDto update(@RequestBody ClienteDto cliente, @PathVariable Long id) {
        cliente.setId(id);
        return clienteService.update(cliente);
    }


}
