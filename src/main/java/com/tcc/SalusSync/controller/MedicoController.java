package com.tcc.SalusSync.controller;


import com.tcc.SalusSync.dto.AgendaDtoList;
import com.tcc.SalusSync.model.Medico;
import com.tcc.SalusSync.dto.MedicoDto;
import com.tcc.SalusSync.service.AgendaService;
import com.tcc.SalusSync.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/medico")
@RestController
public class MedicoController {

    @Autowired
    private MedicoService service;

    @Autowired
    private AgendaService agendaService;

//    @PostMapping("/cadastro")
//    public ResponseEntity<String> cadastroUsuario(@RequestBody MedicoDto dados){
//
//        Medico medico = new Medico(dados.nome(),dados.cpf(),dados.crm(),dados.login(),dados.password(),dados.especializacao());
//        return service.castrarMedico(medico);
//
//    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity<String> deletarMedico(@PathVariable long id){
      return   service.deleteMedico(id);
    }


    @GetMapping("/agendamentos{cpf}")
    public List<AgendaDtoList> agendamentos(@PathVariable String cpf){
        return   agendaService.listaDeAgendamentosMedico(cpf);
    }
}
