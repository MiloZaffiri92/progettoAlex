package com.example.demo.service;

import com.example.demo.entity.Docente;
import com.example.demo.repository.DocenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteService {

    private final DocenteRepository repo;

    public DocenteService(DocenteRepository repo) {
        this.repo = repo;
    }

    public List<Docente> findAll() {
        return repo.findAll();
    }

    public Docente get(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public Docente save(Docente d) {
        return repo.save(d);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
