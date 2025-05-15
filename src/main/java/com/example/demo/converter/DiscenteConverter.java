package com.example.demo.converter;

import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.entity.Discente;

public class DiscenteConverter {
    public static DiscenteDTO toDTO(Discente discente) {
        return new DiscenteDTO(discente.getId(), discente.getNome(), discente.getCognome(), discente.getMatricola(), discente.getCittaResidenza());
    }

    public static Discente toEntity(DiscenteDTO dto) {
        return new Discente(dto.getId(), dto.getNome(), dto.getCognome(), dto.getMatricola(), dto.getCittaResidenza());
    }
}
