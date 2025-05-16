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

    private Long id;
    private String nome;
    private String annoAccademico;

    //usato per il form
    private Long docenteId;
    private List<Long> discentiIds;

    //usato per la lista
    private DocenteDTO docente;
    private List<DiscenteDTO> discenti;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(Long docenteId) {
        this.docenteId = docenteId;
    }

    public List<Long> getDiscentiIds() {
        return discentiIds;
    }

    public void setDiscentiIds(List<Long> discentiIds) {
        this.discentiIds = discentiIds;
    }

}
