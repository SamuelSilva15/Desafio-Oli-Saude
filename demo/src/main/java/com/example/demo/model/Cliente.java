package com.example.demo.model;

import javax.persistence.Entity;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Cliente {
    private String nome;
    private SimpleDateFormat dataDeNascimento = new SimpleDateFormat("dd/MM/yyyy");
    private String sexo;
    private String problemasDeSaude;
    private String dataDeCriação = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm:ss"));;
    private String dataDeAtualização;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public SimpleDateFormat getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(SimpleDateFormat dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getProblemasDeSaude() {
        return problemasDeSaude;
    }

    public void setProblemasDeSaude(String problemasDeSaude) {
        this.problemasDeSaude = problemasDeSaude;
    }

    public String getDataDeCriação() {
        return dataDeCriação;
    }

    public void setDataDeCriação(String dataDeCriação) {
        this.dataDeCriação = dataDeCriação;
    }

    public String getDataDeAtualização() {
        return dataDeAtualização;
    }

    public void setDataDeAtualização(String dataDeAtualização) {
        this.dataDeAtualização = dataDeAtualização;
    }
}
