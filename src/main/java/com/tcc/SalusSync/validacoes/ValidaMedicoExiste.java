package com.tcc.SalusSync.validacoes;

import com.tcc.SalusSync.dto.AgendaDto;
import com.tcc.SalusSync.model.Medico;
import com.tcc.SalusSync.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidaMedicoExiste {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico medicoExiste(AgendaDto agendaDto){
        Medico medico = new Medico();

        if (medicoRepository.findMedicoByCpf(agendaDto.medicoCpf()).isEmpty()){
            throw new RuntimeException("MEDICO NÃO EXISTE");
        }

        Optional<Medico> OpMedico = medicoRepository.findMedicoByCpf(agendaDto.medicoCpf());

        if (OpMedico.isPresent())
            medico = OpMedico.get();
        return medico;

    }
}
