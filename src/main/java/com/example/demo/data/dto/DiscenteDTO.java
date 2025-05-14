package com.example.demo.data.dto;

import com.example.demo.data.entity.Corso;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscenteDTO {

    private Long id;
    private String nome;
    private String cognome;
    private Integer matricola;
    private String cittaResidenza;
    private List<Corso> corsi;
}
