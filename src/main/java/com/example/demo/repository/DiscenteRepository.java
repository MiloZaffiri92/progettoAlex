package com.example.demo.repository;

import com.example.demo.data.entity.Discente;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiscenteRepository extends JpaRepository<Discente,Long> {

    @EntityGraph(attributePaths = "corsi")
    List<Discente> findAll();

    @Query("SELECT d FROM Discente d WHERE d.cittaResidenza = :citta")
    List<Discente> findByCitta(@Param("citta") String citta);

}
