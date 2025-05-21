package com.example.demo.service;

import com.example.demo.converter.Converter;
import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocenteService {

    @Autowired
    private CorsoRepository corsoRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    // Restituisce tutti i docenti come DTO
    public List<DocenteDTO> findAll() {
        return docenteRepository.findAll().stream()
                .map(Converter::toDto)
                .collect(Collectors.toList());
    }

    // Restituisce un singolo docente come DTO
    public DocenteDTO get(Long id) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente non trovato"));
        return Converter.toDto(docente);
    }

    // Salva un docente a partire dal DTO
    public DocenteDTO save(DocenteDTO dto) {
        Docente docente = Converter.toEntity(dto);
        Docente saved = docenteRepository.save(docente);
        return Converter.toDto(saved);
    }

    // Elimina un docente e scollega i corsi
    public void delete(Long id) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente non trovato"));

        List<Corso> corsi = corsoRepository.findByDocente(docente);
        for (Corso corso : corsi) {
            corso.setDocente(null);
            corsoRepository.save(corso);
        }

        docenteRepository.delete(docente);
    }

    public DocenteDTO updateDocente(Long id, DocenteDTO docente) {
        Docente updateDocente = docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente non trovato"));
        if(docente.getNome() != null) updateDocente.setNome(docente.getNome());
        if(docente.getCognome() != null) updateDocente.setCognome(docente.getCognome());
        Docente saved = docenteRepository.save(updateDocente);
        return Converter.toDto(saved);
    }



    // Cerca docenti per nome (parziale o completo)
    public List<DocenteDTO> findByNome(String nome) {
        return docenteRepository.cercaPerNome(nome).stream()
                .map(Converter::toDto)
                .collect(Collectors.toList());
    }
}
