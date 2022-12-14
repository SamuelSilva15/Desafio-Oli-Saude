package com.example.demo.controller;

import com.example.demo.exception.ClienteNotFoundException;
import com.example.demo.model.cliente.Cliente;
import com.example.demo.model.dto.ClienteDTO;
import com.example.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public Cliente salvaCliente(@RequestBody ClienteDTO cliente){
        return clienteService.salvaCliente(cliente);
    }

    @GetMapping
    public List<Cliente> findAll(){
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public Cliente findById(@PathVariable Long id) throws ClienteNotFoundException {
        return clienteService.findByID(id);
    }

    @DeleteMapping("/{id}")
    public void deletaCliente(@PathVariable Long id){
        clienteService.deletaCliente(id);
    }

    @PutMapping("/{id}")
    public Cliente AtualizaDados(@PathVariable Long id, @RequestBody ClienteDTO cliente) throws ClienteNotFoundException {
        return clienteService.atualizaDados(id, cliente);
    }

    @GetMapping("/clientesRisco")
    public List<Cliente> retornaDezClientesComMaiorRisco(){
        return clienteService.retornaDezMaioresClientesComProblemas();
    }
}
