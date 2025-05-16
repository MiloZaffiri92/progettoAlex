package com.example.demo.service;

import com.example.demo.converter.CorsoMapper;
import com.example.demo.converter.CorsoModelMapperConverter;
import com.example.demo.data.dto.CorsoDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CorsoService {

    @Autowired
    private CorsoModelMapperConverter corsoModelMapperConverter;

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private DiscenteRepository discenteRepository;


    @Autowired
    private CorsoRepository corsoRepository;

    @Autowired
    private CorsoMapper corsoMapper;




    @EntityGraph(attributePaths = {"docente", "discente"})
    public List<CorsoDTO> findAll() {
        return corsoRepository.findAll().stream()
                .map(corso -> corsoModelMapperConverter.corsoToDto(corso))
                .collect(Collectors.toList());
    }

    public CorsoDTO findById(Long id) {
        Corso corso = corsoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Corso non trovato"));
        return corsoModelMapperConverter.corsoToDto(corso);
    }


    @EntityGraph(attributePaths = {"docente", "discente"})
    public CorsoDTO save(CorsoDTO dto) {
        Corso corso = corsoMapper.corsoToEntity(dto);

        // risolvi docente da id
        if (dto.getDocenteId() != null) {
            docenteRepository.findById(dto.getDocenteId()).ifPresent(corso::setDocente);
        } else {
            corso.setDocente(null);
        }

        // risolvi discenti da lista id
        if (dto.getDiscentiIds() != null && !dto.getDiscentiIds().isEmpty()) {
            List<Discente> discenti = dto.getDiscentiIds().stream()
                    .map(id -> discenteRepository.findById(id).orElse(null))
                    .filter(d -> d != null)
                    .collect(Collectors.toList());
            corso.setDiscenti(discenti);
        } else {
            corso.setDiscenti(Collections.emptyList());
        }

        Corso saved = corsoRepository.save(corso);
        return corsoMapper.corsoToDto(saved);
    }


    public void deleteById(Long id) {
        corsoRepository.deleteById(id);
    }

    public List<CorsoDTO> findByNome(String nome) {
        return corsoRepository.findByNomeContainingIgnoreCase(nome).stream()
                .map(corso -> corsoModelMapperConverter.corsoToDto(corso))
                .collect(Collectors.toList());
    }
}
