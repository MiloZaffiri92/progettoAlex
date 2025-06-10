package com.example.demo.controller;

import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.entity.Discente;
import com.example.demo.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@RestController
@RequestMapping("/discenti")
public class DiscenteController {

    @Autowired
    private DiscenteService discenteService;

    // Visualizza la lista dei discenti
    @GetMapping("/lista")
    public List<DiscenteDTO> list() {
        return discenteService.findAll();
    }

    // Salva il nuovo Discente
    @PostMapping
    public DiscenteDTO create(@RequestBody DiscenteDTO discente) {
        return discenteService.save(discente);
    }

    @GetMapping("/findById")
    public DiscenteDTO findById(@RequestParam Long id) {
        return discenteService.findById(id);
    }


    // Aggiorna un Discente esistente
    @PutMapping("/modifica/{id}")
    public ResponseEntity<DiscenteDTO> update(@PathVariable Long id,
                                 @RequestBody DiscenteDTO discente) {
        DiscenteDTO updateDiscente = discenteService.updateDiscente(id, discente);
        return ResponseEntity.ok(updateDiscente);
    }

    // Elimina un Discente
    @DeleteMapping("/{id}/delete")
    public void delete(@PathVariable Long id) {
        discenteService.delete(id);
    }

    // Ricerca discenti per città (Query personalizzata)
    @GetMapping("/cerca")
    public ResponseEntity<List<DiscenteDTO>> findByCitta(@RequestParam String citta) {
        List<DiscenteDTO> risultatiRicerca;
        if (citta != null && !citta.isEmpty()) {
            risultatiRicerca = discenteService.findByCitta(citta); // Esegui la query personalizzata
        } else {
            risultatiRicerca = discenteService.findAll(); // In caso di ricerca vuota, ricarica tutti i discenti
        }

        return ResponseEntity.ok(risultatiRicerca);
    }

    @GetMapping("/findByNomeAndCognome")
    public Long findIdByNomeAndCognome(@RequestParam String nome, @RequestParam String cognome) {
        return discenteService.findIdByNomeAndCognome(nome, cognome);
    }



}
