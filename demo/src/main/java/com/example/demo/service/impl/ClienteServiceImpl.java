package com.example.demo.service.impl;

import com.example.demo.exception.ClienteNotFoundException;
import com.example.demo.model.cliente.Cliente;
import com.example.demo.model.dto.ClienteDTO;
import com.example.demo.model.dto.ProblemaDeSaudeDTO;
import com.example.demo.model.problema.ProblemaDeSaude;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    public Cliente salvaCliente(ClienteDTO cliente) {
        Cliente c = new Cliente();
        copiaCliente(c, cliente);
        cliente.setDataDeCriacao(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm:ss")));
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

    @Override
    public Cliente atualizaDados(Long id, ClienteDTO cliente) throws ClienteNotFoundException {;
        Cliente c = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));

        Optional<Cliente> v = clienteRepository.findById(id);
        if (v.isPresent()) {
            Cliente entity = v.get();
            //fazer copia
            if(cliente.getNome() != null){
                entity.setNome(cliente.getNome());
            }
            if(cliente.getDataDeAtualizacao() != null) {
                entity.setDataDeNascimento(cliente.getDataDeNascimento());
            }
            if(cliente.getSexo() != null) {
                entity.setSexo(cliente.getSexo());
            }

            entity.setDataDeAtualizacao(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm:ss")));
            throw new ClienteNotFoundException(id);
        }

        copiaCliente(c, cliente);
        return clienteRepository.save(c);
    }

    public void copiaCliente(Cliente cliente, ClienteDTO clienteDTO){
        cliente.setNome(clienteDTO.getNome());
        cliente.setSexo(clienteDTO.getSexo());
        cliente.setDataDeCriacao(clienteDTO.getDataDeCriacao());
        cliente.setDataDeAtualizacao(clienteDTO.getDataDeAtualizacao());
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
        problemaDeSaude.setGrau(problemaDeSaudeDTO.getGrau());
    }
}
