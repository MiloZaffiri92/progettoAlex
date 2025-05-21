package com.example.demo.controller;

import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.entity.Docente;
import com.example.demo.service.DocenteService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/docenti")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    // LISTA
    @GetMapping("/lista")
    public List<DocenteDTO> list() {
        return docenteService.findAll();
    }


    // SALVA NUOVO
    @PostMapping
    public DocenteDTO create(@RequestBody @Validated DocenteDTO docentedto) {
        return docenteService.save(docentedto);
    }


    // AGGIORNA
    @PutMapping("/{id}")
    public ResponseEntity<DocenteDTO> update(@PathVariable Long id,
                                            @RequestBody DocenteDTO docentedto) {
        DocenteDTO updateDocente = docenteService.updateDocente(id, docentedto);
        return ResponseEntity.ok(updateDocente);
    }


    // DELETE
    @DeleteMapping("/{id}/delete")
    public void delete(@PathVariable Long id) {
        docenteService.delete(id);
    }


    @GetMapping("/cerca")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<DocenteDTO>> cercaPerNome(@RequestParam String nome ) {
        List<DocenteDTO> docenti = docenteService.findByNome(nome);

        return ResponseEntity.ok(docenti);
    }
}

