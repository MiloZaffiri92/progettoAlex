package com.example.demo.repository;



import com.example.demo.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocenteRepository extends JpaRepository<Docente, Long> {

    @Query("SELECT d FROM Docente d WHERE d.nome = :nome")
    List<Docente> cercaPerNome(@Param("nome") String nome);
}
