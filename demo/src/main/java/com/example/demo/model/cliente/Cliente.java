package com.example.demo.model.cliente;

import com.example.demo.model.problema.ProblemaDeSaude;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente implements Comparable<Cliente> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataDeNascimento;
    private String sexo;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataDeCriacao;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataDeAtualizacao;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_codigo")
    private List<ProblemaDeSaude> problemasDeSaude;

    public Integer calculaGraus() {
        var sd = 0;
        if(problemasDeSaude != null) {
            for (ProblemaDeSaude problemaDeSaude : problemasDeSaude) {
                if(problemaDeSaude.getGrau() != null) {
                    sd += problemaDeSaude.getGrau().getCodigo();
                } else {
                    System.out.println(codigo);
                }
            }
        } else {
            System.out.println("ts:" + codigo);
        }
        return sd;
    }

    public Double getScore() {
        var score = (1 / (1 + Math.E-(-2.8 + calculaGraus()))) * 100;

        return score;
    }

    @Override
    public int compareTo(Cliente o) {
        return Comparator.comparing(Cliente::getScore).reversed().compare(this, o);
    }
}
