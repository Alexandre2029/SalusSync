package com.tcc.SalusSync.controller;

import com.tcc.SalusSync.dto.HealthData.*;
import com.tcc.SalusSync.service.BatimentoService;
import com.tcc.SalusSync.service.CaloriesService;
import com.tcc.SalusSync.service.OxigenioService;
import com.tcc.SalusSync.service.PressaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/HealthData")
public class HealthDataController {

    @Autowired
    private BatimentoService batimentoService;

    @Autowired
    private CaloriesService caloriesService;
    @Autowired
    private OxigenioService oxigenioService;
    @Autowired
    private PressaoService pressaoService;

    @PostMapping("/batimento")
    public ResponseEntity<String> resgistrarBatimento(@RequestBody BtmDataDto dadosBatimento){
        return batimentoService.salvarRegistro(dadosBatimento);
    }

    @PostMapping("/batimentos")
    public ResponseEntity<String>registrarBatimentos(@RequestBody BtmDataListDto healthDataListDto){
       return batimentoService.salvarRegistros(healthDataListDto);
    }

    @GetMapping("/batimento{cpf}")
    public  ResponseEntity<BtmDataListDto> ListBatimentos(@PathVariable String cpf ) {
        return  batimentoService.batimentosList(cpf);
    }

    @PostMapping("/calorias")
    public ResponseEntity<String> registrarCalorias(@RequestBody CaloriasDto dadosCalorias){
        return caloriesService.salvarRegistro(dadosCalorias);
    }

    @PostMapping("/caloriasList")
    public ResponseEntity<String>registrarListaCalorias(@RequestBody CaloriasListDto caloriasList){
        return caloriesService.salvarRegistros(caloriasList);
    }

    @GetMapping("/calorias{cpf}")
    public  ResponseEntity<CaloriasListDto> ListCalorias(@PathVariable String cpf ) {
        return  caloriesService.caloriasList(cpf);
    }


    @PostMapping("/oxigenio")
    public ResponseEntity<String> registrarOxigenio(@RequestBody OxigenioDto dadosOxigenio) {
        return oxigenioService.salvarRegistro(dadosOxigenio);
    }

    @PostMapping("/oxigenioList")
    public ResponseEntity<String> registrarListaOxigenio(@RequestBody OxigenioListDto oxigenioList) {
        return oxigenioService.salvarRegistros(oxigenioList);
    }

    @GetMapping("/oxigenio{cpf}")
    public ResponseEntity<OxigenioListDto> listOxigenio(@PathVariable String cpf) {
        return oxigenioService.oxigenioList(cpf);
    }

    @PostMapping("/pressao")
    public ResponseEntity<String> registrarPressao(@RequestBody PressaoDto dadosPressao) {
        return pressaoService.salvarRegistro(dadosPressao);
    }

    @PostMapping("/pressaoList")
    public ResponseEntity<String> registrarListaPressao(@RequestBody PressaoListDto pressaoList) {
        return pressaoService.salvarRegistros(pressaoList);
    }

    @GetMapping("/pressao{cpf}")
    public ResponseEntity<PressaoListDto> listPressao(@PathVariable String cpf) {
        return pressaoService.pressaoList(cpf);
    }

}
