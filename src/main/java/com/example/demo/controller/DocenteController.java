package com.example.demo.controller;

import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.entity.Docente;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/docenti")
public class DocenteController {

    @Autowired
    DocenteService docenteService;

    // LISTA
    @GetMapping("/lista")
    public String list(Model model) {
        List<DocenteDTO> docenti = new ArrayList<>();
        docenti = docenteService.findAll();
        model.addAttribute("docenti", docenti);
        return "list-docenti";
    }

    // FORM NUOVO
    @GetMapping("/nuovo")
    public String showAdd(Model model) {
        model.addAttribute("docente", new DocenteDTO());
        return "form-docente";
    }

    // SALVA NUOVO
    @PostMapping
    public String create(@ModelAttribute("docente") DocenteDTO docentedto,
                         BindingResult br) {
        if (br.hasErrors()) return "form-docente";
        docenteService.save(docentedto);
        return "redirect:/docenti/lista";
    }

    // FORM EDIT
    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        model.addAttribute("docente", docenteService.get(id));
        return "form-docente";
    }

    // AGGIORNA
    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("docente") DocenteDTO docentedto,
                         BindingResult br) {
        if (br.hasErrors()) return "form-docente";
        docentedto.setId(id);
        docenteService.save(docentedto);
        return "redirect:/docenti/lista";
    }

    // DELETE
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        docenteService.delete(id);
        return "redirect:/docenti/lista";
    }


    @GetMapping("/perNome")
    public String cercaPerNome(@RequestParam("nome") String nome, Model model) {
        List<DocenteDTO> docenti = docenteService.findByNome(nome);
        model.addAttribute("docenti", docenti);
        return "list-docenti";
    }

}

