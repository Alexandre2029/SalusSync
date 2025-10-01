package com.tcc.SalusSync.repository;

import com.tcc.SalusSync.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Medico m WHERE m.id = :id")
    int deleteMedicoById(long id);

    Optional<Medico> findMedicoByCpf(String cpf);

    Optional<Medico> findByLogin(String login);

    Optional<Medico> findMedicoByCrm(String crm);

}
