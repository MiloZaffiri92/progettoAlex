package com.example.demo.controller;

import ch.qos.logback.core.model.Model;
import com.example.demo.entity.Discente;
import com.example.demo.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("/discenti")
public class DiscenteController {
    @Autowired
    DiscenteService discenteService;

    @GetMapping("/lista")
    public ModelAndView list() {
        List<Discente> discenti = discenteService.findAll();
        ModelAndView mav = new ModelAndView("list-discente");
        mav.addObject("discente", discenti);
        return mav;
    }

//FORM NUOVO
    @GetMapping("/nuovo")
    public ModelAndView showAdd() {
        ModelAndView mav = new ModelAndView("form-discente");
        mav.addObject("discente", new Discente());
        return mav;
    }



    //Salva nuovo
    @PostMapping
    public String create(@ModelAttribute("discente") Discente discente,
                         BindingResult br) {
        if (br.hasErrors()) return "form-discente";
        discenteService.save(discente);
        return "redirect:/discenti/lista";
    }

    // FORM EDIT
    @GetMapping("/{id}/edit")
    public ModelAndView showEdit(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("form-discente");
        mav.addObject("discente", discenteService.get(id));
        return mav;
    }


    // AGGIORNA
    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("discente") Discente discente,
                         BindingResult br) {
        if (br.hasErrors()) return "form-discente";
        discente.setId(id);
        discenteService.save(discente);
        return "redirect:/discenti/lista";
    }

    // DELETE
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        discenteService.delete(id);
        return "redirect:/discenti/lista";
    }

    // Usa ModelAndView per la ricerca dei discenti per città (QUERY PERSONALIZZATA)
    @GetMapping("/perCitta")
    public ModelAndView findByCitta(@RequestParam(required = false) String citta) {
        List<Discente> risultatiRicerca;
        if (citta != null && !citta.isEmpty()) {
            risultatiRicerca = discenteService.findByCitta(citta); // Esegui la query personalizzata
        } else {
            risultatiRicerca = null; // O magari potresti ricaricare tutti i discenti qui, a seconda del comportamento desiderato
        }

        // Restituisci la vista con i discenti trovati
        ModelAndView modelAndView = new ModelAndView("list-discente");
        modelAndView.addObject("discente", discenteService.findAll()); // Ricarica tutti i discenti per la tabella principale
        modelAndView.addObject("risultatiCitta", risultatiRicerca); // Aggiunge i risultati della ricerca con una chiave specifica
        return modelAndView;
    }
}
