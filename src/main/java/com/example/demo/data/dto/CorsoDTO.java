package com.example.demo.data.dto;

import com.example.demo.data.entity.Discente;
import com.example.demo.data.entity.Docente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorsoDTO {

    private Long id;
    private String nome;
    private String annoAccademico;
    private Docente docente;
    private List<Discente> discenti;
}
