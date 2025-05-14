package com.example.demo.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "docenti")
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(unique = true)
    private String email;

    public String getNomeCompleto() {
        return this.nome + " " + this.cognome;
    }


//    public String getNomeCompleto() {
//        return this.nome + " " + this.cognome;
//    }


}
