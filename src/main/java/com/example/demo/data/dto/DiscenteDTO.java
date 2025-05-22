package com.example.demo.data.dto;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DiscenteDTO {

    private String nome;
    private String cognome;
    private Integer matricola;
    private String cittaResidenza;


    public String getNomeCompleto() {
        return this.nome + " " + this.cognome;
    }

    public String getCittaResidenza() {
        return cittaResidenza;
    }

    public void setCittaResidenza(String cittaResidenza) {
        this.cittaResidenza = cittaResidenza;
    }

    public Integer getMatricola() {
        return matricola;
    }

    public void setMatricola(Integer matricola) {
        this.matricola = matricola;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
}
