package com.tcc.SalusSync.validacoes;

import com.tcc.SalusSync.dto.AgendaDto;
import com.tcc.SalusSync.model.Usuario;
import com.tcc.SalusSync.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidaUsuarioExiste {
    @Autowired
    private UsuarioRepository usuarioRepository;


    public  Usuario UsuarioExiste(String cpf){
        Usuario usuario = new Usuario();
        if (usuarioRepository.findByCpf(cpf).isEmpty()) {
            throw new RuntimeException("USUARIO NÃO EXISTE");
        }
        Optional<Usuario> opUsuario = usuarioRepository.findByCpf(cpf);
        if (opUsuario.isPresent())
            usuario = opUsuario.get();
        return usuario;
    }
}
