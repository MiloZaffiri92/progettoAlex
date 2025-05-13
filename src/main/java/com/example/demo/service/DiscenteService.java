package com.example.demo.service;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DiscenteService {

    @Autowired
    DiscenteRepository discenteRepository;
    @Autowired
    CorsoRepository corsoRepository;

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
        // Trova il discente da eliminare
        Discente discente = discenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discente non trovato"));

        // Trova tutti i corsi che contengono questo discente
        List<Corso> corsi = corsoRepository.findByDiscentiContaining(discente);

        // Rimuove il discente da ciascun corso
        for (Corso corso : corsi) {
            corso.getDiscenti().remove(discente);
            corsoRepository.save(corso); // Salva le modifiche al corso
        }

        // Ora puoi eliminare il discente
        discenteRepository.delete(discente);
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
