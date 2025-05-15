package com.example.demo.controller;

import com.example.demo.data.dto.CorsoDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.service.CorsoService;
import com.example.demo.service.DiscenteService;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@Controller
@RequestMapping("/corsi")
public class CorsoController {

    @Autowired
    private CorsoService corsoService;

    @Autowired
    private DocenteService docenteService;

    @Autowired
    private DiscenteService discenteService;

    @GetMapping("/list")
    public String list(Model model) {
        List<CorsoDTO> corsi = corsoService.findAll();
        model.addAttribute("corsi", corsi);
        return "list-corsi";
    }

    @GetMapping("/nuovo")
    public String nuovoCorsoForm(Model model) {
        model.addAttribute("corso", new CorsoDTO());
        model.addAttribute("docentiList", docenteService.findAll()); // assicurati che restituisca DTO
        model.addAttribute("discentiList", discenteService.findAll()); // idem
        return "form-corso";
    }

    @PostMapping
    public String salvaCorso(@ModelAttribute CorsoDTO corsoDTO) {
        corsoService.save(corsoDTO);
        return "redirect:/corsi/list";
    }

    @GetMapping("/{id}/edit")
    public String modificaCorso(@PathVariable Long id, Model model) {
        CorsoDTO corsoDTO = corsoService.findById(id);
        model.addAttribute("corso", corsoDTO);
        model.addAttribute("docentiList", docenteService.findAll());
        model.addAttribute("discentiList", discenteService.findAll());
        return "form-corso";
    }

    @GetMapping("/{id}/delete")
    public String eliminaCorso(@PathVariable Long id) {
        corsoService.deleteById(id);
        return "redirect:/corsi/list";
    }
}
