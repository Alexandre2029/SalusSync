package com.tcc.SalusSync.validacoes;

import com.tcc.SalusSync.dto.AgendaDto;
import com.tcc.SalusSync.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidarHorarioAgenda {
    @Autowired
    private AgendaRepository agendaRepository;

    //RETORNA TRUE SE EXISTE AGENDAMENTO E FALSE SE NÃO EXISTE
    public boolean validarHorario(AgendaDto agendaDto){
      LocalDateTime inicio = agendaDto.data().minusHours(1);
      LocalDateTime fim = agendaDto.data().plusHours(1);

      var existe=  agendaRepository.existeAgendamentoNoHorario(agendaDto.medicoCpf(), inicio, fim);
        if(existe){
            return true;
        }else
            return false;
    }

}
