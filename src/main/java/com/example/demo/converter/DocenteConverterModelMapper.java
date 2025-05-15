package com.example.demo.converter;

import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.entity.Docente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DocenteConverterModelMapper {

    @Autowired
    private ModelMapper modelMapper;

    public DocenteDTO toDto(Docente entity) {
        return modelMapper.map(entity, DocenteDTO.class);
    }

    public Docente toEntity(DocenteDTO dto) {
        return modelMapper.map(dto, Docente.class);
    }
}
