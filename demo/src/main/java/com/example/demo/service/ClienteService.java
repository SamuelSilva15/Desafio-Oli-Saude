package com.example.demo.service;

import com.example.demo.exception.ClienteNotFoundException;
import com.example.demo.model.Cliente;

import java.util.List;

public interface ClienteService {

    Cliente salvaCliente(Cliente cliente);
    List<Cliente> findAll();
    Cliente findByID(Long id) throws ClienteNotFoundException;
    void deletaCliente(Long id);

    Cliente atualizaDados(Long id, Cliente cliente) throws ClienteNotFoundException;
}
