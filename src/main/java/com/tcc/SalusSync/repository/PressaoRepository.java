package com.tcc.SalusSync.repository;

import com.tcc.SalusSync.model.Oxigenio;
import com.tcc.SalusSync.model.Pressao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PressaoRepository extends JpaRepository<Pressao, Long> {
    List<Pressao> findAllByUsuarioCpf(String cpf);
}
