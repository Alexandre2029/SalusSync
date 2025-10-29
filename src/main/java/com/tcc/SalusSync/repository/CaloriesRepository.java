package com.tcc.SalusSync.repository;

import com.tcc.SalusSync.model.Calorias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaloriesRepository extends JpaRepository<Calorias, Long> {

    List<Calorias> findAllByUsuarioCpf(String cpf);
}
