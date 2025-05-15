package com.example.demo.converter;

import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.entity.Discente;
import com.example.demo.data.entity.Docente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiscenteModelMapperConverter {

    @Autowired
    private ModelMapper modelMapper;

    public DiscenteDTO toDto(Discente entity) {
        return modelMapper.map(entity, DiscenteDTO.class);
    }

    public Discente toEntity(DocenteDTO dto) {
        return modelMapper.map(dto, Discente.class);
    }

}
