package com.example.demo.service;

import com.example.demo.converter.Converter;
import com.example.demo.converter.DiscenteConverter;
import com.example.demo.converter.DiscenteMapper;
import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;

import com.example.demo.data.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscenteService {

    @Autowired
    private DiscenteRepository discenteRepository;
    @Autowired
    private CorsoRepository corsoRepository;
    @Autowired
    private DiscenteMapper discenteMapper;



    public List<DiscenteDTO> findAll() {
        List<DiscenteDTO> discenti = discenteRepository.findAll().stream()
                .map(discenteMapper::toDto)
                .collect(Collectors.toList());
        System.out.println("Numero di discenti recuperati: " + discenti.size());  // Debug
        return discenti;
    }

    public DiscenteDTO get(Long id) {
        Discente discente = discenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Discente non trovato con id: " + id));
        return discenteMapper.toDto(discente);
    }

    public DiscenteDTO save(DiscenteDTO d){
        Discente discente = DiscenteConverter.toEntity(d);
        Discente savedDiscente=discenteRepository.save(discente);
        return DiscenteConverter.toDTO(savedDiscente);
    }

    public DiscenteDTO updateDiscente(Long id, DiscenteDTO discente) {
        Discente updateDiscente = discenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente non trovato"));
        if(discente.getNome() != null) updateDiscente.setNome(discente.getNome());
        if(discente.getCognome() != null) updateDiscente.setCognome(discente.getCognome());
        if(discente.getMatricola() != null) updateDiscente.setMatricola(discente.getMatricola());
        if(discente.getCittaResidenza() != null) updateDiscente.setCittaResidenza(discente.getCittaResidenza());
        Discente discente1 = discenteRepository.save(updateDiscente);
        return DiscenteConverter.toDTO(discente1);
    }


    @Transactional
    public void delete(Long id) {
        Discente discente = discenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Discente non trovato con id: " + id));

        List<Corso> corsi = corsoRepository.findByDiscentiContaining(discente);
        for (Corso corso : corsi) {
            corso.getDiscenti().remove(discente);
            corsoRepository.save(corso);
        }

        discenteRepository.delete(discente);
    }

    @Transactional
    public List<DiscenteDTO> findByCitta(String citta) {
        if (citta == null || citta.trim().isEmpty()) {
            throw new IllegalArgumentException("La città non può essere vuota");
        }
        return discenteRepository.findByCitta(citta).stream()
                .map(discenteMapper::toDto)
                .collect(Collectors.toList());
    }
}
