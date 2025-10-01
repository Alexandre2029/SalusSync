package com.tcc.SalusSync.service;

import com.tcc.SalusSync.repository.AgendaRepository;
import com.tcc.SalusSync.model.Usuario;
import com.tcc.SalusSync.repository.UsuarioRepository;
import com.tcc.SalusSync.util.ValidadorCPF;
import com.tcc.SalusSync.util.ValidadorEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
   private UsuarioRepository repository;
    @Autowired
    private AgendaRepository agendaRepository;

    public ResponseEntity<String> cadastrar(Usuario usuario) {
        // VALIDAÇÕES

        { // Verifica se o CPF é válido
            if (!ValidadorCPF.validarCpf(usuario.getCpf())) {
                return ResponseEntity.badRequest().body("CPF INVÁLIDO");
            }

            // Verifica se já existe um usuário com o CPF informado
            if (repository.findUsuarioByCpf(usuario.getCpf()).isPresent()) {
                return ResponseEntity.badRequest().body("JÁ EXISTE CADASTRO PARA ESSE CPF");
            }

            // Verifica se o email é válido
            if (!ValidadorEmail.validarEmail(usuario.getLogin())) {
                return ResponseEntity.badRequest().body("EMAIL INVALIDO");
            }

            // Verifica se o email já está cadastrado
            if (repository.findByLogin(usuario.getLogin()).isPresent()) {
                return ResponseEntity.badRequest().body("EMAIL JÁ CASTRADO");
            }
        }

        // Salva o novo usuário
        repository.save(usuario);
        return ResponseEntity.ok("USUÁRIO CADASTRADO COM SUCESSO");
    }


    public ResponseEntity<String> removerUsuario(long id) {

        //Deleta os Agendamentos que contem o usuario antes de deletar o Usuario
        agendaRepository.deleteByUsuarioId(id);

        var usuarioDeletado= repository.deleteUsuarioById(id);

        if (usuarioDeletado == 1){
            return ResponseEntity.ok("usuario deletado");
        }else {
            return ResponseEntity.badRequest().body("Usuario não encontrado");

        }
    }


    public String GetContatoEmergencia(long id) {

      var us = repository.findById(id);

      if ((us.isPresent())){
        return   us.get().getContadoEmergencia();
      } else
          return "erro";

    }
}
