package com.tcc.SalusSync.repository;

import com.tcc.SalusSync.model.Batimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BatimentoRepository extends JpaRepository<Batimento, Long> {


    List<Batimento> findAllByUsuarioCpf(String cpf);
}
