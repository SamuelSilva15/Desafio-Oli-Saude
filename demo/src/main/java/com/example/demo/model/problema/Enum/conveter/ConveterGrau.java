package com.example.demo.model.problema.Enum.conveter;

import com.example.demo.model.problema.Enum.GrauEnum;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

public class ConveterGrau implements AttributeConverter<GrauEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(GrauEnum grauEnum) {
        if(grauEnum == null){
            return null;
        }
        return grauEnum.getCodigo();
    }

    @Override
    public GrauEnum convertToEntityAttribute(Integer codigo) {
        if(codigo == null){
            return null;
        }
        return Stream.of(GrauEnum.values())
                .filter(c -> c.getCodigo().equals(codigo))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
