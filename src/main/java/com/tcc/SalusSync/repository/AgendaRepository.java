package com.tcc.SalusSync.repository;

import com.tcc.SalusSync.model.Agenda;
import lombok.experimental.PackagePrivate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    @Transactional
    @Modifying
    void deleteByMedicoId(long id);

    @Transactional
    @Modifying
    void deleteByUsuarioId(long id);


    List<Agenda> findAllByUsuarioId(long id);

    @Modifying
    @Transactional
    @Query("UPDATE Agenda a SET a.situacao = :situacao WHERE a.id = :id")
    int atualizarSituacao(Long id,String situacao);

    @Query("SELECT CASE WHEN COUNT(a) > 0" +
            " THEN true ELSE false END FROM Agenda a WHERE a.medico.cpf " +
            "= :medicoCpf AND a.data BETWEEN :inicio AND :fim ")
    boolean existeAgendamentoNoHorario(String medicoCpf,LocalDateTime inicio,
                                       LocalDateTime fim);

    List<Agenda> findAllByMedicoCpf(String cpf);

}
