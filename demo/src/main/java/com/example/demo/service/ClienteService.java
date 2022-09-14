package com.example.demo.service;

import com.example.demo.exception.ClienteNotFoundException;
import com.example.demo.model.Cliente;
import com.example.demo.service.impl.ClienteServiceImpl;

public interface ClienteService {

    Cliente salvaCliente(Cliente cliente);
    Cliente findByID(Long id) throws ClienteNotFoundException;
    void deletaCliente(Long id);

    Cliente atualizaDados(Long id, Cliente cliente) throws ClienteNotFoundException;
}
