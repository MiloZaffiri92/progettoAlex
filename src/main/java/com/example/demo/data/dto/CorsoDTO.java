package com.example.demo.data.dto;

import com.example.demo.data.entity.Discente;
import com.example.demo.data.entity.Docente;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CorsoDTO {

    private String nome;
    private String annoAccademico;
    private DocenteDTO docente;
    private List<DiscenteDTOLight> discenti;


    public List<DiscenteDTOLight> getDiscenti() {
        return discenti;
    }

    public void setDiscenti(List<DiscenteDTOLight> discenti) {
        this.discenti = discenti;
    }

    public DocenteDTO getDocente() {
        return docente;
    }

    public void setDocente(DocenteDTO docente) {
        this.docente = docente;
    }

    public String getAnnoAccademico() {
        return annoAccademico;
    }

    public void setAnnoAccademico(String annoAccademico) {
        this.annoAccademico = annoAccademico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
