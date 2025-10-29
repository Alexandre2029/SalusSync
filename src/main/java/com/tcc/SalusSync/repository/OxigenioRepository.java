package com.tcc.SalusSync.repository;

import com.tcc.SalusSync.model.Calorias;
import com.tcc.SalusSync.model.Oxigenio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OxigenioRepository extends JpaRepository<Oxigenio, Long> {

    List<Oxigenio> findAllByUsuarioCpf(String cpf);
}
