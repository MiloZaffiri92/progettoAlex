package com.example.demo.service;

import com.example.demo.converter.CorsoMapper;
import com.example.demo.data.dto.CorsoDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.data.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CorsoService {

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private DiscenteRepository discenteRepository;

    @Autowired
    private CorsoRepository corsoRepository;
    @Autowired
    private CorsoMapper corsoMapper;



    public List<CorsoDTO> findAll() {
        return corsoRepository.findAll().stream()
                .map(corsoMapper::corsoToDto)
                .collect(Collectors.toList());
    }

//    public CorsoDTO findById(Long id) {
//        Corso corso = corsoRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Corso non trovato"));
//        return corsoMapper.corsoToDto(corso);
//    }

    @EntityGraph(attributePaths = {"docente", "discente"})
    public CorsoDTO save(CorsoDTO corsoDTO){
        Corso corso = corsoMapper.corsoToEntity(corsoDTO);

        corso.setNome(corsoDTO.getNome());
        corso.setAnnoAccademico(corsoDTO.getAnnoAccademico());

        //Gestiamo Docenti
        if (corsoDTO.getDocente() != null) {
            String nomeDocente = corsoDTO.getDocente().getNome();
            String cognomeDocente = corsoDTO.getDocente().getCognome();
            Docente docente = docenteRepository.findByNomeAndCognome(nomeDocente, cognomeDocente)
                    .orElseGet(() -> {
                        Docente nuovoDocente = new Docente();
                        nuovoDocente.setNome(nomeDocente);
                        nuovoDocente.setCognome(cognomeDocente);
                        // Genera una email temporanea
                        nuovoDocente.setEmail(nomeDocente.toLowerCase() + "." +
                                cognomeDocente.toLowerCase() + "@example.com");
                        return docenteRepository.save(nuovoDocente);
                    });
            corso.setDocente(docente);
        }


        //gestiamo i discenti
        if (corsoDTO.getDiscenti() != null && !corsoDTO.getDiscenti().isEmpty()) {
            List<Discente> discenti = corsoDTO.getDiscenti().stream()
                    .map(discenteDTO -> {
                        return discenteRepository.findByNomeAndCognome(
                                        discenteDTO.getNome(),
                                        discenteDTO.getCognome())
                                .orElseGet(() -> {
                                    Discente nuovoDiscente = new Discente();
                                    nuovoDiscente.setNome(discenteDTO.getNome());
                                    nuovoDiscente.setCognome(discenteDTO.getCognome());
                                    nuovoDiscente.setMatricola(
                                            new Random().nextInt(900000) + 100000);
                                    return discenteRepository.save(nuovoDiscente);
                                });
                    })
                    .collect(Collectors.toList());
            corso.setDiscenti(discenti);
        } else {
            corso.setDiscenti(List.of());
        }
        Corso savedCorso = corsoRepository.save(corso);
        return corsoMapper.corsoToDto(savedCorso);
    }

    public void deleteById(Long id) {
        corsoRepository.deleteById(id);
    }


    public CorsoDTO updateCorso(Long id, CorsoDTO corso) {
        Corso updateCorso = corsoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Corso non trovato"));

        if(corso.getNome() != null) updateCorso.setNome(corso.getNome());
        if(corso.getAnnoAccademico() != null) updateCorso.setAnnoAccademico(corso.getAnnoAccademico());

        // Gestione docente
        if(corso.getDocente() != null) {
            String nomeDocente = corso.getDocente().getNome();
            String cognomeDocente = corso.getDocente().getCognome();
            Docente docente = docenteRepository.findByNomeAndCognome(nomeDocente, cognomeDocente)
                    .orElseGet(() -> {
                        Docente nuovoDocente = new Docente();
                        nuovoDocente.setNome(nomeDocente);
                        nuovoDocente.setCognome(cognomeDocente);
                        // Genera una email temporanea
                        nuovoDocente.setEmail(nomeDocente.toLowerCase() + "." +
                                cognomeDocente.toLowerCase() + "@example.com");
                        return docenteRepository.save(nuovoDocente);
                    });
            updateCorso.setDocente(docente);
        }

        // Gestione discenti
        if(corso.getDiscenti() != null) {
            List<Discente> discenti = corso.getDiscenti().stream()
                    .map(discenteDTO -> {
                        return discenteRepository.findByNomeAndCognome(
                                        discenteDTO.getNome(),
                                        discenteDTO.getCognome())
                                .orElseGet(() -> {
                                    Discente nuovoDiscente = new Discente();
                                    nuovoDiscente.setNome(discenteDTO.getNome());
                                    nuovoDiscente.setCognome(discenteDTO.getCognome());
                                    // Genera una matricola casuale
                                    nuovoDiscente.setMatricola(
                                            new Random().nextInt(900000) + 100000);
                                    return discenteRepository.save(nuovoDiscente);
                                });
                    })
                    .collect(Collectors.toList());
            updateCorso.setDiscenti(discenti);
        }

        Corso saved = corsoRepository.save(updateCorso);
        return corsoMapper.corsoToDto(saved);
    }


}
