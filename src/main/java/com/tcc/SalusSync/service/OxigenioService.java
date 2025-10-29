package com.tcc.SalusSync.service;

import com.tcc.SalusSync.dto.HealthData.*;
import com.tcc.SalusSync.model.Calorias;
import com.tcc.SalusSync.model.Oxigenio;
import com.tcc.SalusSync.model.Usuario;
import com.tcc.SalusSync.repository.OxigenioRepository;
import com.tcc.SalusSync.validacoes.ValidaUsuarioExiste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OxigenioService {

    @Autowired
    private OxigenioRepository oxigenioRepository;
    @Autowired
    private ValidaUsuarioExiste validaUsuarioExiste;

    public ResponseEntity<String> salvarRegistro(OxigenioDto dados) {
        Usuario usuario = validaUsuarioExiste.UsuarioExiste(dados.cpf());
        Oxigenio oxigenio = new Oxigenio(dados.data(), dados.oxigenio(), usuario);
        oxigenioRepository.save(oxigenio);
        return ResponseEntity.ok("Registro salvo com sucesso");
    }

    public ResponseEntity<String> salvarRegistros(OxigenioListDto dadosLista) {
        Usuario usuario = validaUsuarioExiste.UsuarioExiste(dadosLista.cpf());
        List<Oxigenio> oxigenio = dadosLista.dados().stream()
                .map(d -> new Oxigenio(d.data(), d.oxigenio(), usuario))
                .toList();

       oxigenioRepository.saveAll(oxigenio);

        return ResponseEntity.ok("Registros salvos com sucesso");
    }


    public ResponseEntity<OxigenioListDto> oxigenioList(String cpf) {
        List<OxigenioReturnDto> ListOxigenio = oxigenioRepository.findAllByUsuarioCpf(cpf).stream()
                .map(b -> new OxigenioReturnDto(b.getHora(), b.getOxigenio())).toList();
        return ResponseEntity.ok(new OxigenioListDto(cpf, ListOxigenio));

    }



}
