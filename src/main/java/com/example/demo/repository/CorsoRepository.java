package com.example.demo.repository;

import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.data.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CorsoRepository extends JpaRepository<Corso, Long> {
    List<Corso> findByNomeContainingIgnoreCase(String nome);
    List<Corso> findByDocente(Docente docente);

    List<Corso> findByDiscentiContaining(Discente discente);


}
