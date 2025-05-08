package com.example.demo.service;
import com.example.demo.entity.Discente;
import com.example.demo.repository.DiscenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DiscenteService {

    @Autowired
    DiscenteRepository discenteRepository;

    public List<Discente> findAll() {
        return discenteRepository.findAll();
    }

    public Discente get(Long id) {
        return discenteRepository.findById(id).orElseThrow();
    }

    public Discente save(Discente d) {
        return discenteRepository.save(d);
    }

    public void delete(Long id) {
        discenteRepository.deleteById(id);
    }

    // Recupera i discenti in base alla città

    @Transactional
    public List<Discente> findByCitta(String citta) {
        if (citta == null || citta.isEmpty()) {
            throw new IllegalArgumentException("La città non può essere vuota");
        }
        return discenteRepository.findByCitta(citta);
    }



}
