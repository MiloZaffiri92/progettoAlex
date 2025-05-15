package com.example.demo.converter;

import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.entity.Discente;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {CorsoMapper.class})
public interface DiscenteMapper {

    DiscenteDTO toDto(Discente discente);

    Discente toEntity(DiscenteDTO dto);
}
