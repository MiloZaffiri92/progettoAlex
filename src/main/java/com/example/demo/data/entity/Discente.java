package com.example.demo.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "discenti")
@ToString(exclude = "corsi")
@Getter
@Setter
public class Discente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(unique = true, name = "matricola")
    private Integer matricola;

    @Column(name = "eta")
    private Integer eta;

    @Column(name = "citta_residenza")
    private String cittaResidenza;

    @ManyToMany(mappedBy = "discenti")
    private List<Corso> corsi;

    public Discente(Long id, String nome, String cognome, Integer matricola, String cittaResidenza) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
        this.cittaResidenza = cittaResidenza;
    }

    public String getNomeCompleto() {
        return this.nome + " " + this.cognome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discente)) return false;
        Discente that = (Discente) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
