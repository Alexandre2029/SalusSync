package com.tcc.SalusSync.repository;

import com.tcc.SalusSync.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findUsuarioByCpf(String cpf);

    Optional<Usuario> findByLogin(String login);

    Optional<Usuario>findByCpf(String cpf);


    @Transactional
    @Modifying
    @Query("DELETE FROM Usuario u WHERE u.id = :id")
    int deleteUsuarioById(long id);

    @Transactional
    @Modifying
    @Query("UPDATE Usuario u SET u.altura = :altura WHERE u.cpf = :cpf")
    int atualizarAlturaPorCpf(String cpf, double altura);


}
