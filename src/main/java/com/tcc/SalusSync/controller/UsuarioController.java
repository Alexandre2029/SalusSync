package com.tcc.SalusSync.controller;

import com.tcc.SalusSync.dto.AgendaDtoList;
import com.tcc.SalusSync.dto.BatimentoDtoList;
import com.tcc.SalusSync.model.Usuario;
import com.tcc.SalusSync.dto.UsuarioDto;
import com.tcc.SalusSync.service.AgendaService;
import com.tcc.SalusSync.service.BatimentoService;
import com.tcc.SalusSync.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/usuario")
@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @Autowired
    private AgendaService agendaService;

    @Autowired
    private BatimentoService batimentoService;

    @PostMapping("/cadastro")
    public ResponseEntity<String> CadastroUsuario(@RequestBody UsuarioDto dados) {
        Usuario usuario = new Usuario(dados.nome(), dados.cpf(), dados.email(), dados.senha(), dados.altura(), dados.peso(), dados.contato());
        return  service.cadastrar(usuario);
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity<String> DeletarUsuario(@PathVariable long id){
        return service.removerUsuario(id);
    }


    //Retorna os agendamentos do usuario
    @GetMapping("/agendamentos{id}")
    public List<AgendaDtoList> agendamentos(@PathVariable long id){
      return   agendaService.listaDeAgendamentosUsuario(id);
    }

    @GetMapping("/batimentos{id}")
    public List<BatimentoDtoList> batimentos(@PathVariable long id){
        return batimentoService.batimentosList(id) ;
    }

     @GetMapping("/Cemergencia{id}")
    public String retornaContato(@PathVariable long id){

        return service.GetContatoEmergencia(id);

     }


    }




