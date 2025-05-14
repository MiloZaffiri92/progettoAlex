package com.example.demo.service;
import com.example.demo.converter.Converter;
import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscenteService {

    @Autowired
    DiscenteRepository discenteRepository;
    @Autowired
    CorsoRepository corsoRepository;

    // Restituisce tutti i discenti in formato DTO
    public List<DiscenteDTO> findAll() {
        return discenteRepository.findAll().stream()
                .map(Converter::discenteToDto)
                .collect(Collectors.toList());
    }

    // Ottieni un discente specifico, restituendo un DTO
    public DiscenteDTO get(Long id) {
        Discente discente = discenteRepository.findById(id).orElseThrow(() -> new RuntimeException("Discente non trovato"));
        return Converter.discenteToDto(discente);
    }

    // Salva un discente, ricevendo un DTO e restituendo un DTO
    public DiscenteDTO save(DiscenteDTO dto) {
        Discente discente = Converter.discenteToEntity(dto);
        Discente saved = discenteRepository.save(discente);
        return Converter.discenteToDto(saved);
    }

    // Elimina un discente, dato un id
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

    // Recupera i discenti in base alla città, restituendo DTO
    @Transactional
    public List<DiscenteDTO> findByCitta(String citta) {
        if (citta == null || citta.isEmpty()) {
            throw new IllegalArgumentException("La città non può essere vuota");
        }
        List<Discente> discenti = discenteRepository.findByCitta(citta);
        return discenti.stream()
                .map(Converter::discenteToDto)
                .collect(Collectors.toList());
    }

}
