package com.example.demo.service;
import com.example.demo.entity.Discente;
import com.example.demo.repository.DiscenteRepository;
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
}
