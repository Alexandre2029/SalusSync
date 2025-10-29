package com.tcc.SalusSync.controller;

import com.tcc.SalusSync.dto.ContatoDto;
import com.tcc.SalusSync.dto.ContatoListDto;
import com.tcc.SalusSync.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ContatoController {
    @Autowired
    private ContatoService contatoService;

    @PostMapping("/contato")
    public ResponseEntity<String> registrarContato(@RequestBody ContatoDto dadosContato) {
        return contatoService.salvarRegistro(dadosContato);
    }

    @PostMapping("/contatoList")
    public ResponseEntity<String> registrarListaContato(@RequestBody ContatoListDto contatoList) {
        return contatoService.salvarRegistros(contatoList);
    }

    @GetMapping("/contato/{cpf}")
    public ResponseEntity<ContatoListDto> listContato(@PathVariable String cpf) {
        return contatoService.contatoList(cpf);
    }

}
