package com.example.demo.converter;


import com.example.demo.data.dto.CorsoDTO;
import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.entity.Corso;

import com.example.demo.data.entity.Docente;
import com.example.demo.repository.DocenteRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.data.entity.Discente;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CorsoModelMapperConverter {


    private final ModelMapper modelMapper;

    public CorsoModelMapperConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        // NON includere la PropertyMap da Corso a CorsoDTO che causava problemi.
        // La mappatura da Corso a CorsoDTO sarà gestita interamente nel metodo corsoToDto.

        // Manteniamo il mapping da DTO a Entity, dato che gli ID sono gestiti nel service
        this.modelMapper.addMappings(new PropertyMap<CorsoDTO, Corso>() {
            @Override
            protected void configure() {
                // Ignora il mapping diretto di oggetti Docente/Discenti dal DTO all'entità.
                // Il service si occuperà di recuperare/associare le entità Docente/Discenti
                // usando docenteId e discentiIds dal DTO.
                skip(destination.getDocente());
                skip(destination.getDiscenti());
            }
        });
    }

    public CorsoDTO corsoToDto(Corso corso) {
        if (corso == null) return null;

        // 1. Mappa i campi semplici da Corso a CorsoDTO (id, nome, annoAccademico).
        // ModelMapper lo fa automaticamente se i nomi dei campi corrispondono.
        CorsoDTO dto = new CorsoDTO();
        dto.setId(corso.getId()); // Manuale per chiarezza o se i nomi differiscono
        dto.setNome(corso.getNome());
        dto.setAnnoAccademico(corso.getAnnoAccademico());
        // In alternativa, se i nomi dei campi sono identici e non ci sono conflitti:
        // CorsoDTO dto = modelMapper.map(corso, CorsoDTO.class);
        // MA questo potrebbe provare a mappare anche docente/discenti in modi imprevisti
        // se non configurato attentamente per escluderli o gestirli.
        // Per maggiore controllo, procediamo con la mappatura esplicita dei campi complessi.


        // 2. Gestisci Docente e DocenteId
        if (corso.getDocente() != null) {
            Docente docenteEntity = corso.getDocente(); // Assicurati che sia inizializzato!
            dto.setDocenteId(docenteEntity.getId());
            // Mappa l'entità Docente a DocenteDTO
            // Questo funzionerà bene se ModelMapper è configurato per Docente -> DocenteDTO
            // o se i nomi dei campi corrispondono e non ci sono conversioni complesse.
            dto.setDocente(modelMapper.map(docenteEntity, DocenteDTO.class));
        }

        // 3. Gestisci Discenti e DiscentiIds
        if (corso.getDiscenti() != null && !corso.getDiscenti().isEmpty()) {
            // Assicurati che la lista e i suoi elementi siano inizializzati!
            List<Long> discentiIds = corso.getDiscenti().stream()
                    .map(Discente::getId)
                    .collect(Collectors.toList());
            dto.setDiscentiIds(discentiIds);

            // Mappa la lista di entità Discente a una lista di DiscenteDTO
            List<DiscenteDTO> discenteDtos = corso.getDiscenti().stream()
                    .map(discenteEntity -> modelMapper.map(discenteEntity, DiscenteDTO.class))
                    .collect(Collectors.toList());
            dto.setDiscenti(discenteDtos);
        } else {
            dto.setDiscentiIds(Collections.emptyList());
            dto.setDiscenti(Collections.emptyList());
        }

        return dto;
    }

    public Corso corsoToEntity(CorsoDTO dto) {
        if (dto == null) return null;
        // ModelMapper mapperà i campi semplici (id, nome, annoAccademico) e gli ID.
        // La PropertyMap<CorsoDTO, Corso> gestisce lo skip di docente/discenti.
        Corso entity = modelMapper.map(dto, Corso.class);
        // Il service poi userà docenteId e discentiIds per associare le entità corrette.
        return entity;
    }

}
