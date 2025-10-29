package com.tcc.SalusSync.service;


import com.tcc.SalusSync.dto.HealthData.CaloriasDto;
import com.tcc.SalusSync.dto.HealthData.CaloriasListDto;
import com.tcc.SalusSync.dto.HealthData.CaloriasReturnDto;
import com.tcc.SalusSync.model.Calorias;
import com.tcc.SalusSync.model.Usuario;
import com.tcc.SalusSync.repository.CaloriesRepository;
import com.tcc.SalusSync.validacoes.ValidaUsuarioExiste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaloriesService {


    @Autowired
    private CaloriesRepository caloriesRepository;
    @Autowired
    private ValidaUsuarioExiste validaUsuarioExiste;

    public ResponseEntity<String> salvarRegistro(CaloriasDto dados) {
        Usuario usuario = validaUsuarioExiste.UsuarioExiste(dados.cpf());
        Calorias calories = new Calorias(dados.data(), dados.calorias(), usuario);
        caloriesRepository.save(calories);
        return ResponseEntity.ok("Registro salvo com sucesso");
    }

    public ResponseEntity<String> salvarRegistros(CaloriasListDto dadosLista) {
        Usuario usuario = validaUsuarioExiste.UsuarioExiste(dadosLista.cpf());
        List<Calorias> calories = dadosLista.dados().stream()
                .map(d -> new Calorias(d.data(), d.calorias(), usuario))
                .toList();

        caloriesRepository.saveAll(calories);

        return ResponseEntity.ok("Registros salvos com sucesso");
    }


    public ResponseEntity<CaloriasListDto> caloriasList(String cpf) {

        List<CaloriasReturnDto> Calories = caloriesRepository.findAllByUsuarioCpf(cpf).stream()
                .map(b -> new CaloriasReturnDto(b.getHora(), b.getCalorias())).toList();

        return ResponseEntity.ok(new CaloriasListDto(cpf, Calories));

    }

    }





