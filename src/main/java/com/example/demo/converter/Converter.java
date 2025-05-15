package com.example.demo.converter;

import com.example.demo.data.dto.CorsoDTO;
import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.data.entity.Docente;

public class Converter {


    // Entity → DTO
    public static DocenteDTO toDto(Docente docente) {
        if (docente == null) return null;

        return new DocenteDTO(
                docente.getId(),
                docente.getNome(),
                docente.getCognome()
        );
    }

    // DTO → Entity
    public static Docente toEntity(DocenteDTO dto) {
        if (dto == null) return null;

        Docente docente = new Docente();
        docente.setId(dto.getId());
        docente.setNome(dto.getNome());
        docente.setCognome(dto.getCognome());


        return docente;
    }

}
