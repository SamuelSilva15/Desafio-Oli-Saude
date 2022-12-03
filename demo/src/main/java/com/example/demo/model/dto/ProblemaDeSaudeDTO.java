package com.example.demo.model.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemaDeSaudeDTO {

    @NotNull
    private String nome;
    private Integer grau;
}
