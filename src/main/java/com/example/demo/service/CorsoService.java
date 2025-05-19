package com.example.demo.service;


import com.example.demo.converter.CorsoModelMapperConverter;
import com.example.demo.data.dto.CorsoDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;

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




    public List<CorsoDTO> findAll() {
        return corsoRepository.findAll().stream()
                .map(corso -> corsoModelMapperConverter.toDto(corso))
                .collect(Collectors.toList());
    }

    public CorsoDTO findById(Long id) {
        Corso corso = corsoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Corso non trovato"));
        return corsoModelMapperConverter.toDto(corso);
    }

    @EntityGraph(attributePaths = {"docente", "discente"})
    public CorsoDTO save(CorsoDTO c){
        Corso corso = corsoModelMapperConverter.toEntity(c);

        if (c.getDocenteId() != null) {
            corso.setDocente(docenteRepository.findById(c.getDocenteId()).orElse(null));
        }

        if (c.getDiscentiIds() != null && !c.getDiscentiIds().isEmpty()) {
            corso.setDiscenti(discenteRepository.findAllById(c.getDiscentiIds()));
        } else {
            corso.setDiscenti(List.of());
        }

        Corso savedCorso = corsoRepository.save(corso);
        return corsoModelMapperConverter.toDto(savedCorso);
    }

    public void deleteById(Long id) {
        corsoRepository.deleteById(id);
    }

    public List<CorsoDTO> findByNome(String nome) {
        return corsoRepository.findByNomeContainingIgnoreCase(nome).stream()
                .map(corso -> corsoModelMapperConverter.toDto(corso))
                .collect(Collectors.toList());
    }


}
