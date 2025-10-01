package com.tcc.SalusSync.controller;


import com.tcc.SalusSync.dto.AgendaDto;
import com.tcc.SalusSync.dto.AgendaDtoSituacao;
import com.tcc.SalusSync.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/agenda")
@RestController
public class AgendaController {
    @Autowired
    private AgendaService service;

    @PostMapping("/agendar")

    public ResponseEntity<String> agendarConsulta(@RequestBody AgendaDto dadosAgendamento){
        return service.salvarAgendamento(dadosAgendamento);
    }

    @PutMapping("/AlterarSituacao")
    public ResponseEntity<String> alterarSitu(@RequestBody AgendaDtoSituacao AgSituacao){
        return  service.alterarSituacao(AgSituacao);
    }

}
