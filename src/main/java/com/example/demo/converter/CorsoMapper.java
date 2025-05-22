package com.example.demo.converter;

import com.example.demo.data.dto.CorsoDTO;
import com.example.demo.data.entity.Corso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CorsoMapper {
    @Mapping(target = "docente", source = "docente")
    @Mapping(target = "discenti", source = "discenti")
    CorsoDTO corsoToDto(Corso corso);

    @Mapping(target = "docente", ignore = true)
    @Mapping(target = "discenti", ignore = true)
    Corso corsoToEntity(CorsoDTO corsoDTO);
}
