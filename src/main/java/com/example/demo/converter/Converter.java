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

    public static DiscenteDTO discenteToDto(Discente discente) {

        if (discente == null) return null;

        return new DiscenteDTO(
                discente.getId(),
                discente.getNome(),
                discente.getCognome(),
                discente.getMatricola(),
                discente.getCittaResidenza(),
                discente.getCorsi()
        );
    }

    public static Discente discenteToEntity(DiscenteDTO dto) {
        if (dto == null) return null;

        Discente discente = new Discente();
        discente.setId(dto.getId());
        discente.setNome(dto.getNome());
        discente.setCognome(dto.getCognome());
        discente.setMatricola(dto.getMatricola());
        discente.setCittaResidenza(dto.getCittaResidenza());
        discente.setCorsi(dto.getCorsi());
        return discente;
    }





}
