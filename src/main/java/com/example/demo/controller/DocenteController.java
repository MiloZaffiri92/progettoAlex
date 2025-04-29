package com.example.demo.controller;

import com.example.demo.entity.Docente;
import com.example.demo.service.DocenteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/docenti")
public class DocenteController {

    private final DocenteService service;

    public DocenteController(DocenteService service) {
        this.service = service;
    }

    // LISTA
    @GetMapping
    public String list(Model model) {
        model.addAttribute("docenti", service.findAll());
        return "list-docenti";
    }

    // FORM NUOVO
    @GetMapping("/new")
    public String showAdd(Model model) {
        model.addAttribute("docente", new Docente());
        return "form-docente";
    }

    // SALVA NUOVO
    @PostMapping
    public String create(@ModelAttribute("docente") Docente docente,
                         BindingResult br) {
        if (br.hasErrors()) return "form-docente";
        service.save(docente);
        return "redirect:/docenti";
    }

    // FORM EDIT
    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        model.addAttribute("docente", service.get(id));
        return "form-docente";
    }

    // AGGIORNA
    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("docente") Docente docente,
                         BindingResult br) {
        if (br.hasErrors()) return "form-docente";
        docente.setId(id);
        service.save(docente);
        return "redirect:/docenti";
    }

    // DELETE
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/docenti";
    }








}

