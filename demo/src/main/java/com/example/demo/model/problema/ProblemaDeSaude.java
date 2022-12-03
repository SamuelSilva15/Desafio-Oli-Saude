package com.example.demo.model.problema;

import com.example.demo.model.problema.Enum.GrauEnum;
import com.example.demo.model.problema.Enum.conveter.ConveterGrau;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "problema_saude")
public class ProblemaDeSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nome;

    @Convert(converter = ConveterGrau.class)
    private GrauEnum grau;
}


