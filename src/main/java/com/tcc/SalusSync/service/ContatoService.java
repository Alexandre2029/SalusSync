package com.tcc.SalusSync.service;

import com.tcc.SalusSync.dto.ContatoDto;
import com.tcc.SalusSync.dto.ContatoListDto;
import com.tcc.SalusSync.dto.ContatoReturnDto;
import com.tcc.SalusSync.dto.HealthData.CaloriasDto;
import com.tcc.SalusSync.dto.HealthData.CaloriasListDto;
import com.tcc.SalusSync.dto.HealthData.CaloriasReturnDto;
import com.tcc.SalusSync.model.Calorias;
import com.tcc.SalusSync.model.Contato;
import com.tcc.SalusSync.model.Usuario;
import com.tcc.SalusSync.repository.ContatoRepository;
import com.tcc.SalusSync.validacoes.ValidaUsuarioExiste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;
    @Autowired
    private ValidaUsuarioExiste validaUsuarioExiste;

    public ResponseEntity<String> salvarRegistro(ContatoDto dados) {
        Usuario usuario = validaUsuarioExiste.UsuarioExiste(dados.cpf());
        Contato contato = new Contato(dados.contato(), usuario);
        contatoRepository.save(contato);
        return ResponseEntity.ok("Registro salvo com sucesso");
    }

    public ResponseEntity<String> salvarRegistros(ContatoListDto dadosLista) {
        Usuario usuario = validaUsuarioExiste.UsuarioExiste(dadosLista.cpf());
        List<Contato> contato = dadosLista.dados().stream()
                .map(d -> new Contato( d.contato(), usuario))
                .toList();

        contatoRepository.saveAll(contato);

        return ResponseEntity.ok("Registros salvos com sucesso");
    }

    public ResponseEntity<ContatoListDto> contatoList(String cpf) {

        List<ContatoReturnDto> contatos = contatoRepository.findAllByUsuarioCpf(cpf).stream()
                .map(b -> new ContatoReturnDto( b.getContatoEmergencia()))
                .toList();

        return ResponseEntity.ok(new ContatoListDto(cpf, contatos));
    }

}
