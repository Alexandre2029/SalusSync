package com.tcc.SalusSync.service;


import com.tcc.SalusSync.dto.BatimentoDto;
import com.tcc.SalusSync.dto.BatimentoDtoList;
import com.tcc.SalusSync.model.Batimento;
import com.tcc.SalusSync.model.Usuario;
import com.tcc.SalusSync.repository.BatimentoRepository;
import com.tcc.SalusSync.validacoes.ValidaUsuarioExiste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatimentoService {


    @Autowired
    private BatimentoRepository batimentoRepository;
    @Autowired
    private ValidaUsuarioExiste validaUsuarioExiste;

    public ResponseEntity<String> salvarRegistro(BatimentoDto dadosBatimento) {
        Usuario usuario = new Usuario();

        try{
            usuario = validaUsuarioExiste.UsuarioExiste(dadosBatimento.cpf());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Batimento batimento = new Batimento(dadosBatimento.data(), dadosBatimento.batimentos(),usuario);

        batimentoRepository.save(batimento);

        return ResponseEntity.ok( "Registro salvo com sucesso");

    }

    public ResponseEntity<List<BatimentoDtoList>> batimentosList(String cpf){

        var batimentos= batimentoRepository.findAllByUsuarioCpf(cpf);

        return ResponseEntity.ok(batimentos.stream().map(b -> new BatimentoDtoList(b.getHora(), b.getBatimentosMinutos())).collect(Collectors.toList())) ;


    }



}
