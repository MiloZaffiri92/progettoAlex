package com.example.demo.data.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocenteDTO {

    private Long id;
    private String nome;
    private String cognome;

    public String getNomeCompleto() {
        return this.nome + " " + this.cognome;
    }


}
