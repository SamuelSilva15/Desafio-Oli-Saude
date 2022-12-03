package com.example.demo.model.dto;

import com.example.demo.model.problema.ProblemaDeSaude;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    @NotNull
    private String nome;
    @NotNull
    private LocalDate dataDeNascimento;
    @NotNull
    private String sexo;
    @NotNull
    private List<ProblemaDeSaudeDTO> problemasDeSaude;
}
