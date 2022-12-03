package com.example.demo.model.problema.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GrauEnum {

    GRAUUM(1, "Grau um"),
    GRAUDOIS(2, "Grau dois");

    private final Integer codigo;
    private final String nome;

    @JsonCreator
    public static GrauEnum fromValues(@JsonProperty("codigo") Integer codigo,
                                       @JsonProperty("nome") String nome) {
        return Arrays.stream(values()).filter(n -> n.getCodigo().equals(codigo) || n.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    public static GrauEnum fromCodigo(Integer codigo){
        return Arrays.stream(values()).filter(n -> n.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }
}
