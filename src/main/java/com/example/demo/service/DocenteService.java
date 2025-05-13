package com.example.demo.service;

import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteService {

    @Autowired
    CorsoRepository corsoRepository;

    @Autowired
    DocenteRepository docenteRepository;

    public List<Docente> findAll() {
        return docenteRepository.findAll();
    }

    public Docente get(Long id) {
        return docenteRepository.findById(id).orElseThrow();
    }

    public Docente save(Docente d) {
        return docenteRepository.save(d);
    }

    public void delete(Long id) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente non trovato"));

        // Scollega i corsi che fanno riferimento al docente
        List<Corso> corsi = corsoRepository.findByDocente(docente);
        for (Corso corso : corsi) {
            corso.setDocente(null);
            corsoRepository.save(corso);
        }

        // Ora elimina il docente
        docenteRepository.delete(docente);
    }


    public List<Docente> findByNome(String nome) {
        return docenteRepository.cercaPerNome(nome);
    }
}
