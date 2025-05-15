package com.example.demo.controller;

import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.entity.Discente;
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
    private DiscenteService discenteService;

    // Visualizza la lista dei discenti
    @GetMapping("/lista")
    public ModelAndView list() {
        List<DiscenteDTO> discenti = discenteService.findAll();
        System.out.println("Numero di discenti passati alla vista: " + discenti.size());  // Verifica che i discenti siano correttamente passati alla vista
        ModelAndView mav = new ModelAndView("list-discente");
        mav.addObject("discenti", discenti);  // Aggiungi i discenti al modello
        return mav;
    }

    // Form per aggiungere un nuovo Discente
    @GetMapping("/nuovo")
    public ModelAndView showAdd() {
        ModelAndView mav = new ModelAndView("form-discente");
        mav.addObject("discente", new DiscenteDTO()); // Crea un nuovo DTO vuoto
        return mav;
    }

    // Salva il nuovo Discente
    @PostMapping
    public String create(@ModelAttribute("discente") DiscenteDTO discente,
                         BindingResult br) {
        if (br.hasErrors()) return "form-discente"; // Se ci sono errori nel form, torna alla pagina del form
        discenteService.save(discente); // Salva il DTO
        return "redirect:/discenti/lista"; // Redirect alla lista
    }

    // Form per modificare un Discente
    @GetMapping("/{id}/edit")
    public ModelAndView showEdit(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("form-discente");
        mav.addObject("discente", discenteService.get(id)); // Ottieni il DTO tramite id
        return mav;
    }

    // Aggiorna un Discente esistente
    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("discente") DiscenteDTO discente,
                         BindingResult br) {
        if (br.hasErrors()) return "form-discente"; // Se ci sono errori nel form, torna alla pagina del form
        discente.setId(id); // Imposta l'id per l'aggiornamento
        discenteService.save(discente); // Salva il DTO aggiornato
        return "redirect:/discenti/lista"; // Redirect alla lista
    }

    // Elimina un Discente
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        discenteService.delete(id); // Elimina il discente con il dato id
        return "redirect:/discenti/lista"; // Redirect alla lista
    }

    // Ricerca discenti per città (Query personalizzata)
    @GetMapping("/perCitta")
    public ModelAndView findByCitta(@RequestParam(required = false) String citta) {
        List<DiscenteDTO> risultatiRicerca;
        if (citta != null && !citta.isEmpty()) {
            risultatiRicerca = discenteService.findByCitta(citta); // Esegui la query personalizzata
        } else {
            risultatiRicerca = discenteService.findAll(); // In caso di ricerca vuota, ricarica tutti i discenti
        }

        // Restituisce la vista con i risultati della ricerca e la lista principale
        ModelAndView modelAndView = new ModelAndView("list-discente");
        modelAndView.addObject("discenti", discenteService.findAll()); // Ricarica tutti i discenti per la tabella principale
        modelAndView.addObject("risultatiCitta", risultatiRicerca); // Aggiungi i risultati della ricerca
        return modelAndView;
    }
}
