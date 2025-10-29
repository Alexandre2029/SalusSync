package com.tcc.SalusSync.service;

import com.tcc.SalusSync.dto.HealthData.*;
import com.tcc.SalusSync.model.Oxigenio;
import com.tcc.SalusSync.model.Pressao;
import com.tcc.SalusSync.model.Usuario;
import com.tcc.SalusSync.repository.PressaoRepository;
import com.tcc.SalusSync.validacoes.ValidaUsuarioExiste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PressaoService {
    @Autowired
    private ValidaUsuarioExiste validaUsuarioExiste;
    @Autowired
    private PressaoRepository pressaoRepository;

    public ResponseEntity<String> salvarRegistro(PressaoDto dados) {
        Usuario usuario = validaUsuarioExiste.UsuarioExiste(dados.cpf());
        Pressao pressao = new Pressao(dados.data(), dados.pressaoSistolica(), dados.pressaoDiastolica(), usuario);
        pressaoRepository.save(pressao);
        return ResponseEntity.ok("Registro salvo com sucesso");
    }

    public ResponseEntity<String> salvarRegistros(PressaoListDto dadosLista) {
        Usuario usuario = validaUsuarioExiste.UsuarioExiste(dadosLista.cpf());
        List<Pressao> pressao = dadosLista.dados().stream()
                .map(d -> new Pressao(d.data(), d.pressaoSistolica(), d.pressaoDiastolica(), usuario))
                .toList();
        pressaoRepository.saveAll(pressao);
        return ResponseEntity.ok("Registros salvos com sucesso");
    }


    public ResponseEntity<PressaoListDto> pressaoList(String cpf) {
        List<PressaoReturnDto> ListPressao = pressaoRepository.findAllByUsuarioCpf(cpf).stream()
                .map(b -> new PressaoReturnDto(b.getHora(), b.getPressaoSistolica(), b.getPressaoDiastolica())).toList();
        return ResponseEntity.ok(new PressaoListDto(cpf, ListPressao));

    }
}