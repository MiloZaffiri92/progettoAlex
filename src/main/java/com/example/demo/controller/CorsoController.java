package com.example.demo.controller;

import com.example.demo.data.dto.CorsoDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.service.CorsoService;
import com.example.demo.service.DiscenteService;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/corsi")
public class CorsoController {

    @Autowired
    private CorsoService corsoService;

    @Autowired
    private DocenteService docenteService;

    @Autowired
    private DiscenteService discenteService;

    @GetMapping("/list")
    public List<CorsoDTO> list() {
        return corsoService.findAll();
    }

    @PostMapping
    public CorsoDTO salvaCorso(@RequestBody CorsoDTO corsoDTO) {
        return corsoService.save(corsoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CorsoDTO> modificaCorso(@PathVariable Long id,
                                                  @RequestBody CorsoDTO corso) {
        CorsoDTO corsoDTO = corsoService.updateCorso(id, corso);
        return ResponseEntity.ok(corsoDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminaCorso(@PathVariable Long id) {
        corsoService.deleteById(id);
    }
}
