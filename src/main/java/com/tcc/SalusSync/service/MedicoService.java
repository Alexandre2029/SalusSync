package com.tcc.SalusSync.service;

import com.tcc.SalusSync.repository.AgendaRepository;
import com.tcc.SalusSync.model.Medico;
import com.tcc.SalusSync.repository.MedicoRepository;
import com.tcc.SalusSync.util.ValidadorCPF;
import com.tcc.SalusSync.util.ValidadorEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository repository;
    @Autowired
    private AgendaRepository agendaRepository;


    public ResponseEntity<String> castrarMedico(Medico medico) {

        // VALIDAÇÕES
         {
            if (!ValidadorCPF.validarCpf(medico.getCpf())) {
                return ResponseEntity.badRequest().body("CPF INVÁLIDO");
            }

            if (repository.findMedicoByCpf(medico.getCpf()).isPresent()) {
                return ResponseEntity.badRequest().body("JÁ EXISTE CADASTRO PARA ESSE CPF");
            }

            if (repository.findMedicoByCrm(medico.getCrm()).isPresent()) {
                return ResponseEntity.badRequest().body("JÁ EXISTE CADASTRO PARA ESSE CRM");
            }

            if (!ValidadorEmail.validarEmail(medico.getLogin())) {
                return ResponseEntity.badRequest().body("EMAIL INVALIDO");
            }

            if (repository.findByLogin(medico.getLogin())!= null) {
                return ResponseEntity.badRequest().body("EMAIL JÁ CASTRADO");
            }
        }

        repository.save(medico);
        return ResponseEntity.ok("Cadastro realizado com sucesso");

    }

    public ResponseEntity<String> deleteMedico(long id) {

        agendaRepository.deleteByMedicoId(id);

        var medicoDeletado= repository.deleteMedicoById(id);

        if (medicoDeletado == 1){
            return ResponseEntity.ok("usuario deletado");
        }else {
            return ResponseEntity.badRequest().body("Usuario não encontrado");

        }
    }
}
