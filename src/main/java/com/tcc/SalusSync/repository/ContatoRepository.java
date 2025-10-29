package com.tcc.SalusSync.repository;


import com.tcc.SalusSync.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    List<Contato> findAllByUsuarioCpf(String cpf);
}
