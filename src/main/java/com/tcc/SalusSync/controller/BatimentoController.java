package com.tcc.SalusSync.controller;

import com.tcc.SalusSync.dto.BatimentoDto;
import com.tcc.SalusSync.dto.BatimentoDtoList;
import com.tcc.SalusSync.service.BatimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/batimento")
public class BatimentoController {


    @Autowired
    private BatimentoService batimentoService;

    @PostMapping("/registro")
    public ResponseEntity<String> resgistrarBatimento(@RequestBody BatimentoDto dadosBatimento){

        return batimentoService.salvarRegistro(dadosBatimento);
    }

    @GetMapping("/registros{cpf}")
    public  ResponseEntity<List<BatimentoDtoList>> ListBatimentos(@PathVariable String cpf ) {

        return  batimentoService.batimentosList(cpf);

    }





}
