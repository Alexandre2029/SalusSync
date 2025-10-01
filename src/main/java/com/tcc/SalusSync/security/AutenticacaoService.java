package com.tcc.SalusSync.security;

import com.tcc.SalusSync.model.Usuario;
import com.tcc.SalusSync.repository.MedicoRepository;
import com.tcc.SalusSync.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MedicoRepository medicoRepository;


    public AutenticacaoService (UsuarioRepository usuarioRepository, MedicoRepository medicoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.medicoRepository = medicoRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Primeiro tenta no usuário
        return usuarioRepository.findByLogin(username)
                .<UserDetails>map(u -> u) // converte Usuario em UserDetails
                .orElseGet(() ->
                        // se não achar, tenta no médico
                        medicoRepository.findByLogin(username)
                                .<UserDetails>map(m -> m) // converte Medico em UserDetails
                                .orElseThrow(() ->
                                        new UsernameNotFoundException("Usuário ou Médico não encontrado: " + username)
                                )
                );
    }
}
