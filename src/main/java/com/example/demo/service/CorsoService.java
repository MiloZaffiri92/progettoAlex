package com.example.demo.service;

import com.example.demo.data.dto.CorsoDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.repository.CorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CorsoService {
    @Autowired
    private CorsoRepository corsoRepository;

    public List<Corso> findAll() {
        return corsoRepository.findAll();
    }

    public Optional<Corso> findById(Long id) {
        return corsoRepository.findById(id);
    }

    public Corso save(Corso corso) {
        return corsoRepository.save(corso);
    }

    public void deleteById(Long id) {
        corsoRepository.deleteById(id);
    }

    public List<Corso> findByNome(String nome) {
        return corsoRepository.findByNomeContainingIgnoreCase(nome);
    }


}
