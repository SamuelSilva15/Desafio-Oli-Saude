package com.example.demo.model.dto;

import com.example.demo.model.problema.ProblemaDeSaude;
import com.sun.istack.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ClienteDTO {

    @NotNull
    private String nome;
    @NotNull
    private String dataDeNascimento;
    @NotNull
    private String sexo;
    @NotNull
    private String dataDeCriacao;
    @NotNull
    private String dataDeAtualizacao;
    @NotNull
    private List<ProblemaDeSaudeDTO> problemasDeSaude;
}
