package com.tcc.SalusSync.service;


import com.tcc.SalusSync.dto.AgendaDto;
import com.tcc.SalusSync.dto.AgendaDtoList;
import com.tcc.SalusSync.dto.AgendaDtoSituacao;
import com.tcc.SalusSync.model.Medico;
import com.tcc.SalusSync.repository.MedicoRepository;
import com.tcc.SalusSync.model.Agenda;
import com.tcc.SalusSync.model.Usuario;
import com.tcc.SalusSync.repository.AgendaRepository;
import com.tcc.SalusSync.repository.UsuarioRepository;
import com.tcc.SalusSync.validacoes.ValidaUsuarioExiste;
import com.tcc.SalusSync.validacoes.ValidarHorarioAgenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendaService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ValidarHorarioAgenda validarHorarioAgenda;

    @Autowired
    private ValidaUsuarioExiste validaUsuarioExiste;

    public ResponseEntity<String> salvarAgendamento(AgendaDto agendaDto) {
         Usuario usuario = new Usuario();
         Medico medico = new Medico();

        try{
            usuario = validaUsuarioExiste.UsuarioExiste(agendaDto.medicoCpf());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        if (medicoRepository.findMedicoByCpf(agendaDto.medicoCpf()).isEmpty()){
            return ResponseEntity.badRequest().body("Medico Não existem");
        }else{
            Optional<Medico> OPAgenda = medicoRepository.findMedicoByCpf(agendaDto.medicoCpf());
             medico = OPAgenda.get();
        }

        if (validarHorarioAgenda.validarHorario(agendaDto)){
            return ResponseEntity.badRequest().body("HORARIO NÃO DISPONIVEL");
        }


        Agenda agenda = new Agenda(agendaDto.descricao(),agendaDto.data(),usuario,medico);

        String mensagem = "VOCÊ POSSUI UM NOVO AGENDAMENTO DE " + agendaDto.descricao();

        emailService.enviarEmail("alexandre781220@gmail.com", "NOVO AGENDAMENTO", mensagem);

        agendaRepository.save(agenda);
        return ResponseEntity.ok("agendamento concluido");

    }





    public void deletarAgendaMedico(long id){
         agendaRepository.deleteByMedicoId(id);

    }

    public void deletarAgendaUsuario(long id){
        agendaRepository.deleteByUsuarioId(id);

    }

    public ResponseEntity<String> alterarSituacao(AgendaDtoSituacao agSituacao) {

        var ag = agendaRepository.findById(agSituacao.ID());

        if(ag.isEmpty()){
            return ResponseEntity.badRequest().body("Agendamento não existe");
        }

        var teste =  agendaRepository.atualizarSituacao(agSituacao.ID(), agSituacao.situacao());

        if(teste >= 1) {
            return ResponseEntity.ok("Situação atualizada");
        }else{
            return  ResponseEntity.badRequest().body("erro");
        }

    }

    public List<AgendaDtoList> listaDeAgendamentosUsuario(long id) {

        var agendas = agendaRepository.findAllByUsuarioId(id);

        return agendas.stream().map(a -> new AgendaDtoList(a.getDescricao(),a.getData())).collect(Collectors.toList());

    }

    public List<AgendaDtoList> listaDeAgendamentosMedico(String cpf) {

        var agendas = agendaRepository.findAllByMedicoCpf(cpf);

        return agendas.stream().map(a -> new AgendaDtoList(a.getDescricao(),a.getData())).collect(Collectors.toList());

    }
}
