package com.tcc.SalusSync.controller;

import com.tcc.SalusSync.dto.AgendaDto;
import com.tcc.SalusSync.dto.BatimentoDto;
import com.tcc.SalusSync.service.AgendaService;
import com.tcc.SalusSync.service.BatimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batimento")
public class BatimentoController {


    @Autowired
    private BatimentoService batimentoService;

    @PostMapping("/registro")
    public ResponseEntity<String> resgistrarBatimento(@RequestBody BatimentoDto dadosBatimento){

        return batimentoService.salvarRegistro(dadosBatimento);
    }




}
