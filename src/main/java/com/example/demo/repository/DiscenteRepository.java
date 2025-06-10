package com.example.demo.repository;

import com.example.demo.data.entity.Discente;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DiscenteRepository extends JpaRepository<Discente,Long> {

    @EntityGraph(attributePaths = "corsi")
    List<Discente> findAll();

    @Query("SELECT d FROM Discente d WHERE d.cittaResidenza = :citta")
    List<Discente> findByCitta(@Param("citta") String citta);

//   Optional<Discente> findByNomeAndCognome(String nome, String cognome);


    @Query("SELECT d FROM Discente d WHERE LOWER(d.nome) = LOWER(:nome) AND LOWER(d.cognome) = LOWER(:cognome)")
    Optional<Discente> findByNomeAndCognome(
            @Param("nome") String nome,
            @Param("cognome") String cognome
    );
    @Query("SELECT d.id FROM Discente d WHERE d.nome=:nome AND d.cognome=:cognome")
    Long findIdByNomeAndCognome(String nome, String cognome);


}
