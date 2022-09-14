package com.example.demo.service.impl;

import com.example.demo.exception.ClienteNotFoundException;
import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService{

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente salvaCliente(Cliente cliente) {
        cliente.setDataDeAtualização(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm:ss")));
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }


    @Override
    public Cliente findByID(Long id) throws ClienteNotFoundException {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
    }

    @Override
    public void deletaCliente(Long id) {
        if(clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
        }
    }

    @Override
    public Cliente atualizaDados(Long id, Cliente cliente) throws ClienteNotFoundException {
        Optional<Cliente> v = clienteRepository.findById(id);
        if (v.isPresent()) {
            Cliente entity = v.get();
            //fazer copia
            entity.setNome(cliente.getNome());
            entity.setDataDeNascimento(cliente.getDataDeNascimento());
            entity.setSexo(cliente.getSexo());
            entity.setProblemasDeSaude(cliente.getProblemasDeSaude());
            entity.setDataDeAtualização(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm:ss")));
            return clienteRepository.save(entity);
        }

        throw new  ClienteNotFoundException(id);
    }
}
