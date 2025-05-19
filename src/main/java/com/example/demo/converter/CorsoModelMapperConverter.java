package com.example.demo.converter;


import com.example.demo.data.dto.CorsoDTO;
import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.repository.DocenteRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.data.entity.Discente;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CorsoModelMapperConverter {

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private DiscenteRepository discenteRepository;

    private final ModelMapper modelMapper;

    public CorsoModelMapperConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        setupMapper();
    }

    private void setupMapper() {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true);

        modelMapper.createTypeMap(Corso.class, CorsoDTO.class).setPostConverter(context -> {
            Corso source = context.getSource();
            CorsoDTO destination = context.getDestination();

            // Mapping base fields
            destination.setId(source.getId());
            destination.setNome(source.getNome());
            destination.setAnnoAccademico(source.getAnnoAccademico());

            // Mapping docente
            if (source.getDocente() != null) {
                destination.setDocenteId(source.getDocente().getId());
                destination.setDocente(modelMapper.map(source.getDocente(), DocenteDTO.class));
            }

            // Mapping discenti
            if (source.getDiscenti() != null) {
                destination.setDiscentiIds(source.getDiscenti().stream()
                        .map(Discente::getId)
                        .collect(Collectors.toList()));
                destination.setDiscenti(source.getDiscenti().stream()
                        .map(d -> modelMapper.map(d, DiscenteDTO.class))
                        .collect(Collectors.toList()));
            }

            return destination;
        });

        // Mapping inverso da DTO an Entity
        modelMapper.createTypeMap(CorsoDTO.class, Corso.class).setPostConverter(context -> {
            CorsoDTO source = context.getSource();
            Corso destination = context.getDestination();

            // Mapping base fields
            destination.setId(source.getId());
            destination.setNome(source.getNome());
            destination.setAnnoAccademico(source.getAnnoAccademico());

            return destination;
        });
    }

    public CorsoDTO toDto(Corso corso) {
        if (corso == null) return null;
        return modelMapper.map(corso, CorsoDTO.class);
    }

    public Corso toEntity(CorsoDTO dto) {
        if (dto == null) return null;
        Corso corso = modelMapper.map(dto, Corso.class);

        // Gestione del docente
        if (dto.getDocenteId() != null) {
            docenteRepository.findById(dto.getDocenteId())
                    .ifPresent(corso::setDocente);
        }

        // Gestione dei discenti
        if (dto.getDiscentiIds() != null && !dto.getDiscentiIds().isEmpty()) {
            corso.setDiscenti(discenteRepository.findAllById(dto.getDiscentiIds()));
        } else {
            corso.setDiscenti(Collections.emptyList());
        }

        return corso;
    }
}



