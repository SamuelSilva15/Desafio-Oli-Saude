package com.example.demo.service;

import com.example.demo.exception.ClienteNotFoundException;
import com.example.demo.model.cliente.Cliente;
import com.example.demo.model.dto.ClienteDTO;
import org.springframework.validation.annotation.Validated;

import java.util.List;

public interface ClienteService {

    Cliente salvaCliente(ClienteDTO clienteDTO);
    List<Cliente> findAll();
    Cliente findByID(Long id) throws ClienteNotFoundException;
    void deletaCliente(Long id);

    Cliente atualizaDados(Long id, ClienteDTO clienteDTO) throws ClienteNotFoundException;
}
