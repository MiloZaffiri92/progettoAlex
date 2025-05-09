package com.example.demo.controller;

import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
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
    public String listCorsi(@RequestParam(required = false) String nome, Model model) {
        List<Corso> corsi = (nome != null && !nome.isEmpty())
                ? corsoService.findByNome(nome)
                : corsoService.findAll();

        model.addAttribute("corsi", corsi);
        return "list-corsi";
    }

    @GetMapping("/nuovo")
    public String nuovoCorsoForm(Model model) {
        model.addAttribute("corso", new Corso());
        model.addAttribute("docentiList", docenteService.findAll());
        model.addAttribute("discentiList", discenteService.findAll());
        return "form-corso"; // jsp per creazione
    }

    @PostMapping
    public String salvaCorso(@ModelAttribute Corso corso) {
        corsoService.save(corso);
        return "redirect:/corsi/list";
    }

    @GetMapping("/{id}/edit")
    public String modificaCorso(@PathVariable Long id, Model model) {
        Corso corso = corsoService.findById(id).orElseThrow();
        corso.getDiscenti().size();





        model.addAttribute("corso", corso);
        model.addAttribute("docentiList", docenteService.findAll());
        model.addAttribute("discentiList", discenteService.findAll());
        return "form-corso";
    }

    @GetMapping("/{id}/delete")
    public String eliminaCorso(@PathVariable Long id) {
        corsoService.deleteById(id);
        return "redirect:/corsi";
    }

}
