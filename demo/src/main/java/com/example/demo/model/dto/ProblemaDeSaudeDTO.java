package com.example.demo.model.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class ProblemaDeSaudeDTO {

    @NotNull
    private String nome;
    @NotNull
    private Integer grau;
}
