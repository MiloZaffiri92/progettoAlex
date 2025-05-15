package com.example.demo.converter;

import com.example.demo.data.dto.CorsoDTO;
import com.example.demo.data.entity.Corso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CorsoMapper {
    @Mapping(target = "docenteId", source = "docente.id")
    @Mapping(target = "discentiIds", expression = "java(corso.getDiscenti().stream().map(d -> d.getId()).toList())")
    CorsoDTO corsoToDto(Corso corso);

    @Mapping(target = "docente.id", source = "docenteId")
    @Mapping(target = "discenti", ignore = true) // li carichi tu
    Corso corsoToEntity(CorsoDTO corsoDTO);
}
//prova