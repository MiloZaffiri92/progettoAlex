package com.example.demo.repository;



import com.example.demo.data.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DocenteRepository extends JpaRepository<Docente, Long> {

    @Query("SELECT d FROM Docente d WHERE d.nome = :nome")
    List<Docente> cercaPerNome(@Param("nome") String nome);

    Optional<Docente> findByNomeAndCognome(String nomeDocente, String cognomeDocente);

    @Query("SELECT d.id FROM Docente d WHERE d.nome=:nome AND d.cognome=:cognome")
    Long findIdByNomeAndCognome(String nome, String cognome);
}
