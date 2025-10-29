package com.tcc.SalusSync.service;


import com.tcc.SalusSync.dto.HealthData.BtmDataDto;
import com.tcc.SalusSync.dto.HealthData.BtmDataListDto;
import com.tcc.SalusSync.dto.HealthData.BtmReturnDto;
import com.tcc.SalusSync.model.Batimento;
import com.tcc.SalusSync.model.Usuario;
import com.tcc.SalusSync.repository.BatimentoRepository;
import com.tcc.SalusSync.validacoes.ValidaUsuarioExiste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatimentoService {


    @Autowired
    private BatimentoRepository batimentoRepository;
    @Autowired
    private ValidaUsuarioExiste validaUsuarioExiste;

    public ResponseEntity<String> salvarRegistro(BtmDataDto dadosBatimento) {
        Usuario usuario = validaUsuarioExiste.UsuarioExiste(dadosBatimento.cpf());
        Batimento batimento = new Batimento(dadosBatimento.data(), dadosBatimento.batimento(), usuario);

        batimentoRepository.save(batimento);

        return ResponseEntity.ok("Registro salvo com sucesso");
    }

    public ResponseEntity<String> salvarRegistros(BtmDataListDto dadosLista) {
        Usuario usuario = validaUsuarioExiste.UsuarioExiste(dadosLista.cpf());

        List<Batimento> batimentos = dadosLista.dados().stream()
                .map(d -> new Batimento(d.data(), d.batimento(), usuario))
                .toList();

        batimentoRepository.saveAll(batimentos);

        return ResponseEntity.ok("Registros salvos com sucesso");
    }


    public ResponseEntity<BtmDataListDto> batimentosList(String cpf){

        List<BtmReturnDto> batimentos=  batimentoRepository.findAllByUsuarioCpf(cpf).stream()
                .map(b -> new BtmReturnDto(b.getHora(),b.getBatimentosMinutos())).toList();

        return ResponseEntity.ok(new BtmDataListDto(cpf, batimentos));

    }



}
