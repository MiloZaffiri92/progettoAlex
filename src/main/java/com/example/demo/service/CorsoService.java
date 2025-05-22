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

    public CorsoDTO findById(Long id) {
        Corso corso = corsoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Corso non trovato"));
        return corsoMapper.corsoToDto(corso);
    }

    @EntityGraph(attributePaths = {"docente", "discente"})
    public CorsoDTO save(CorsoDTO corsoDTO){
        Corso corso = corsoMapper.corsoToEntity(corsoDTO);

        corso.setNome(corsoDTO.getNome());
        corso.setAnnoAccademico(corsoDTO.getAnnoAccademico());

        if (corsoDTO.getDocente() != null) {
            String nomeDocente = corsoDTO.getDocente().getNome();
            String cognomeDocente = corsoDTO.getDocente().getCognome();
            Optional<Docente> docente = docenteRepository.findByNomeAndCognome(nomeDocente, cognomeDocente);
            if (docente.isPresent()) {
                corso.setDocente(docente.get());
            } else {
                throw new RuntimeException("Docente non trovato");
            }
        }

        //gestiamo i discenti
        if (corsoDTO.getDiscenti() != null && !corsoDTO.getDiscenti().isEmpty()) {
            List<Discente> discenti = corsoDTO.getDiscenti().stream()
                    .map(discenteDTO -> {
                        return discenteRepository.findByNomeAndCognome(
                                        discenteDTO.getNome(),
                                        discenteDTO.getCognome())
                                .orElseThrow(() -> new RuntimeException("Discente non trovato"));
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

        if(corso.getDocente() != null) {
            Docente docente = docenteRepository.findByNomeAndCognome(
                    corso.getDocente().getNome(),
                    corso.getDocente().getCognome()
            ).orElseThrow(() -> new RuntimeException("Docente non trovato"));
            updateCorso.setDocente(docente);
        }

        if(corso.getDiscenti() != null) {
            List<Discente> discenti = corso.getDiscenti().stream()
                    .map(discenteDTO -> discenteRepository.findByNomeAndCognome(
                            discenteDTO.getNome(),
                            discenteDTO.getCognome()
                    ).orElseThrow(() -> new RuntimeException("Discente non trovato")))
                    .collect(Collectors.toList());
            updateCorso.setDiscenti(discenti);
        }

        Corso saved = corsoRepository.save(updateCorso);
        return corsoMapper.corsoToDto(saved);
    }

}
