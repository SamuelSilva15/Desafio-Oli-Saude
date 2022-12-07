package com.example.demo.service.impl;

import com.example.demo.exception.ClienteNotFoundException;
import com.example.demo.model.cliente.Cliente;
import com.example.demo.model.dto.ClienteDTO;
import com.example.demo.model.dto.ProblemaDeSaudeDTO;
import com.example.demo.model.problema.Enum.GrauEnum;
import com.example.demo.model.problema.ProblemaDeSaude;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService{

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente salvaCliente(ClienteDTO cliente) {
        Cliente c = new Cliente();
        copiaCliente(c, cliente);
        c.setDataDeCriacao(LocalDateTime.now());
        return clienteRepository.save(c);
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

    //copiando dados
    @Override
    public Cliente atualizaDados(Long id, ClienteDTO cliente) throws ClienteNotFoundException {;
        Cliente c = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));

        copiaCliente(c, cliente);
        c.setDataDeAtualizacao(LocalDateTime.now());

        return clienteRepository.save(c);
    }

    @Override
    public List<Cliente> retornaDezMaioresClientesComProblemas() {
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream().sorted().limit(10).collect(Collectors.toList());
    }

    public void copiaCliente(Cliente cliente, ClienteDTO clienteDTO) {
        cliente.setNome(clienteDTO.getNome());
        cliente.setSexo(clienteDTO.getSexo());
        cliente.setDataDeNascimento(clienteDTO.getDataDeNascimento());

        List<ProblemaDeSaude> problemas = new ArrayList<>();
        for(ProblemaDeSaudeDTO problema : clienteDTO.getProblemasDeSaude()){
            ProblemaDeSaude problemasDeSaude = new ProblemaDeSaude();
            copiaProblemaDeSaude(problemasDeSaude, problema);
            problemas.add(problemasDeSaude);
        }

        cliente.setProblemasDeSaude(problemas);
    }

    public void copiaProblemaDeSaude(ProblemaDeSaude problemaDeSaude, ProblemaDeSaudeDTO problemaDeSaudeDTO) {
        problemaDeSaude.setNome(problemaDeSaudeDTO.getNome());
        problemaDeSaude.setGrau(GrauEnum.fromCodigo(problemaDeSaudeDTO.getGrau()));
    }
}
