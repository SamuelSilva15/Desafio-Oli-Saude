package com.example.demo.model.cliente;

import com.example.demo.model.problema.ProblemaDeSaude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(exclude = "nameAttributeInThisClassWithOneToMany")
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nome;
    private String dataDeNascimento;
    private String sexo;
    private String dataDeCriacao;
    private String dataDeAtualizacao;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_codigo")
    private List<ProblemaDeSaude> problemasDeSaude;
}
